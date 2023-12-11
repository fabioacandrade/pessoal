module ram_1x4(
    input clk,         // Clock signal
    input rst,         // Reset signal
    input r_w,         // Read/Write control signal, 0 for read and 1 for write
    input [3:0] data_in,  // 4-bit data input
    output reg [3:0] data_out // 4-bit data output
);

reg [3:0] memory; // 4-bit register to store the data using JK flip-flops


//Code to write in memory if r_w == 1 and clk is on posedge
	
always @(posedge clk or posedge rst) begin
    if (rst) begin
        memory <= 4'b0;
    end else if (r_w) begin
        memory <= data_in;
    end
end


//Code to read from memory if r_w == 0 and clk is on negedge

always @(negedge clk) begin
	if(!r_w) begin
		data_out <= memory; //Extracting data from memory
	end
end

endmodule

module ram_1x8(
    input clk,
    input rst,
    input r_w,
    input [7:0] data_in,
    input address,
    output [7:0] data_out
);

    wire [3:0] data_out_lower;
    wire [3:0] data_out_upper;

    // Instancia o primeiro módulo ram_1x4 para os LSBs
    
	    ram_1x4 ram_lower(
	        .clk(clk),
	        .rst(rst),
	        .r_w(r_w),
	        .data_in(data_in[3:0]), // Envia os 4 bits menos significativos
	        .data_out(data_out_lower)
	    );

	    // Instancia o segundo módulo ram_1x4 para os MSBs
	    ram_1x4 ram_upper(
	        .clk(clk),
	        .rst(rst),
	        .r_w(r_w),
	        .data_in(data_in[7:4]), // Envia os 4 bits mais significativos
	        .data_out(data_out_upper)
	    );

	    // Combina as saídas dos dois módulos ram_1x4 para formar a saída de 8 bits
	    assign data_out = {data_out_upper, data_out_lower};


endmodule

module ram_2x8(
    input clk,
    input rst,
    input r_w,
    input address, // Assume that this is a 1-bit address
    input [7:0] data_in,
    output [7:0] data_out
);

    wire [7:0] data_out0, data_out1;

    // Instancia os dois módulos de ram_1x8
    ram_1x8 ram0(
        .clk(clk),
        .rst(rst),
        .r_w(r_w & ~address), // Habilita escrita/leitura apenas quando address é 0
        .data_in(data_in),
        .data_out(data_out0)
    );

    ram_1x8 ram1(
        .clk(clk),
        .rst(rst),
        .r_w(r_w & address), // Habilita escrita/leitura apenas quando address é 1
        .data_in(data_in),
        .data_out(data_out1)
    );

    // Lógica para selecionar qual saída de dados usar baseada no endereço
    assign data_out = address ? data_out1 : data_out0;

endmodule

module ram_2x8_tb; 

    // Declaração de sinais para conectar ao módulo de RAM.
    reg clk;
    reg rst;
    reg r_w;
    reg address;
    reg [7:0] data_in;
    wire [7:0] data_out;
	
    ram_2x8 memory0 ( 
        .clk(clk),
        .rst(rst),
        .r_w(r_w),
        .data_in(data_in),
        .address(address),
        .data_out(data_out)
    );

    // Geração do sinal de clock.
    initial begin
        clk = 0;
        forever #5 clk = ~clk; 
    end

    // Procedimento de teste.
    initial begin
        // Inicializa os sinais.
        rst = 1; r_w = 0; data_in = 8'b0; address = 0;
        #10; // Aguarda por 10ns.

        rst = 0; // Tira o reset.
        r_w = 1; // Define o modo para escrita.
        data_in = 8'b10101010; // Valor a ser escrito na memória.
  		address = 0;
        #10; // Aguarda um ciclo de clock.

        r_w = 0; // Muda para o modo de leitura.
        #10; // Aguarda um ciclo de clock.

        r_w = 1;
        data_in = 8'b11111111;
        address = 1;
        #10;

        r_w = 0;
        #10;

		r_w = 1;
		data_in = 8'b10101010;
		#10;

		r_w = 0;
		#10;

        
        // Finaliza a simulação.
        $finish;
    end

    // Adicionar aqui monitoramento de sinais, se necessário.
    initial begin
        $monitor("At time %t, on address %b, data_out = %b", $time, address, data_out);
    end

endmodule
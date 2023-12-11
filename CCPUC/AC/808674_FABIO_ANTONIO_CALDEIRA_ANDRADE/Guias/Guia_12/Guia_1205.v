module ram_1x4(
    input clk,         // Clock signal
    input rst,         // Reset signal
    input r_w,         // Read/Write control signal, 0 for read and 1 for write
    input [3:0] data_in,  // 4-bit data input
    output reg [3:0] data_out // 4-bit data output
);

reg [3:0] jk_ff; // 4-bit register to store the data using JK flip-flops


//Code to write in memory if r_w == 1 and clk is on posedge
	
always @(posedge clk or posedge rst) begin
    if (rst) begin
        jk_ff <= 4'b0;
    end else if (r_w) begin
        jk_ff <= data_in;
    end
end


//Code to read from memory if r_w == 0 and clk is on negedge

always @(negedge clk) begin
	if(!r_w) begin
		data_out <= jk_ff; //Extracting data from memory
	end
end

endmodule

module ram_1x8(
    input clk,
    input rst,
    input r_w,
    input [7:0] data_in,
    input addrs,
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

module ram_8x8(
    input clk,
    input rst,
    input r_w,
    input [2:0] addrs,
    input [7:0] data_in,
    output [7:0] data_out
);

    wire [7:0] data_out0, data_out1, data_out2, data_out3, data_out4, data_out5, data_out6, data_out7;
    wire en0, en1, en2, en3, en4, en5, en6, en7;

    // Decodificação do endereço para habilitar o módulo de RAM correto
    assign en0 = (addrs == 3'b000) & r_w;
    assign en1 = (addrs == 3'b001) & r_w;
    assign en2 = (addrs == 3'b010) & r_w;
    assign en3 = (addrs == 3'b011) & r_w;
    assign en4 = (addrs == 3'b100) & r_w;
    assign en5 = (addrs == 3'b101) & r_w;
    assign en6 = (addrs == 3'b110) & r_w;
    assign en7 = (addrs == 3'b111) & r_w;

    // Instâncias dos módulos de RAM individuais
    ram_1x8 ram000(
        .clk(clk),
        .rst(rst),
        .r_w(en0), // Habilita escrita/leitura apenas quando addrs é 00
        .data_in(data_in),
        .data_out(data_out0)
    );

    ram_1x8 ram001(
        .clk(clk),
        .rst(rst),
        .r_w(en1), // Habilita escrita/leitura apenas quando addrs é 01
        .data_in(data_in),
        .data_out(data_out1)
    );

    ram_1x8 ram010(
        .clk(clk),
        .rst(rst),
        .r_w(en2), // Habilita escrita/leitura apenas quando addrs é 10
        .data_in(data_in),
        .data_out(data_out2)
    );

    ram_1x8 ram011(
        .clk(clk),
        .rst(rst),
        .r_w(en3), // Habilita escrita/leitura apenas quando addrs é 11
        .data_in(data_in),
        .data_out(data_out3)
    );

    ram_1x8 ram100(
        .clk(clk),
        .rst(rst),
        .r_w(en4), // Habilita escrita/leitura apenas quando addrs é 11
        .data_in(data_in),
        .data_out(data_out4)
    );

    ram_1x8 ram101(
        .clk(clk),
        .rst(rst),
        .r_w(en5), // Habilita escrita/leitura apenas quando addrs é 11
        .data_in(data_in),
        .data_out(data_out5)
    );

    ram_1x8 ram110(
        .clk(clk),
        .rst(rst),
        .r_w(en6), // Habilita escrita/leitura apenas quando addrs é 11
        .data_in(data_in),
        .data_out(data_out6)
    );

    ram_1x8 ram111(
        .clk(clk),
        .rst(rst),
        .r_w(en7), // Habilita escrita/leitura apenas quando addrs é 11
        .data_in(data_in),
        .data_out(data_out7)
    );



    // Lógica para selecionar qual saída de dados usar baseada no endereço
    assign data_out = (addrs == 3'b000) ? data_out0 :
                      (addrs == 3'b001) ? data_out1 :
                      (addrs == 3'b010) ? data_out2 :
					  (addrs == 3'b011) ? data_out3 :
                      (addrs == 3'b100) ? data_out4 :
					  (addrs == 3'b101) ? data_out5 :
					  (addrs == 3'b110) ? data_out6 :
					  data_out7;
endmodule



module ram_8x8_tb; 

    // Declaração de sinais para conectar ao módulo de RAM.
    reg clk;
    reg rst;
    reg r_w;
    reg [2:0] addrs;
    reg [7:0] data_in;
    wire [7:0] data_out;
	
    ram_8x8 teste ( 
        .clk(clk),
        .rst(rst),
        .r_w(r_w),
        .data_in(data_in),
        .addrs(addrs),
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
        rst = 1; r_w = 0; data_in = 8'b0; addrs = 3'b0;
        #10; // Aguarda por 10ns.

        rst = 0; // Tira o reset.
        r_w = 1; // Define o modo para escrita.
        data_in = 8'b10101010; // Valor a ser escrito na memória.
  		addrs = 3'b0;
        #10; // Aguarda um ciclo de clock.

        r_w = 0; // Muda para o modo de leitura.
        #10; // Aguarda um ciclo de clock.

        r_w = 1;
        data_in = 8'b11111111;
        addrs = 3'b001;
        #10;

        r_w = 0;
        #10;

		r_w = 1;
		data_in = 8'b10101010;
		addrs = 3'b010;
		#10;

		r_w = 0;
		#10;

		r_w = 1;
		data_in = 8'b00000001;
		addrs = 3'b011;
		#10;

		r_w = 0;
		#10;

		r_w = 1;
		data_in = 8'b10010001;
		addrs = 3'b100;
		#10;

		r_w = 0;
		#10;

		r_w = 1;
		data_in = 8'b00001111;
		addrs = 3'b101;
		#10;

		r_w = 0;
		#10;

		r_w = 1;
		data_in = 8'b00101001;
		addrs = 3'b110;
		#10;

		r_w = 0;
		#10;

		r_w = 1;
		data_in = 8'b10110001;
		addrs = 3'b111;
		#10;

		r_w = 0;
		#10;

        
        // Finaliza a simulação.
        $finish;
    end

    // Adicionar aqui monitoramento de sinais, se necessário.
    initial begin
        $monitor("At time %t, on address %b, data_out = %b", $time, addrs, data_out);
    end

endmodule
module ram_1x4(
    input clk,         // Clock signal
    input rst,         // Reset signal
    input r_w,         // Read/Write control signal, 0 for read and 1 for write
    input [3:0] data_in,  // 4-bit data input
    output reg [3:0] data_out // 4-bit data output
);

reg [3:0] mem; // 4-bit register to store the data using JK flip-flops


//Code to write in memory if r_w == 1 and clk is on posedge
	
always @(posedge clk or posedge rst) begin
    if (rst) begin
        mem <= 4'b0;
    end else if (r_w) begin
        mem <= data_in;
    end
end


//Code to read from memory if r_w == 0 and clk is on negedge

always @(negedge clk) begin
	if(!r_w) begin
		data_out <= mem; //Extracting data from memory
	end
	else begin
		data_out <= 4'bx;
	end	
end

endmodule

module ram_1x4_tb; 

    // Declaração de sinais para conectar ao módulo de RAM.
    reg clk;
    reg rst;
    reg r_w;
    reg [3:0] data_in;
    wire [3:0] data_out;
	
    ram_1x4 uut ( 
        .clk(clk),
        .rst(rst),
        .r_w(r_w),
        .data_in(data_in),
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
        rst = 1; r_w = 0; data_in = 4'b0;
        #10; // Aguarda por 10ns.

        rst = 0; // Tira o reset.
        r_w = 1; // Define o modo para escrita.
        data_in = 4'b1010; // Valor a ser escrito na memória.
        #10; // Aguarda um ciclo de clock.

        r_w = 0; // Muda para o modo de leitura.
        #10; // Aguarda um ciclo de clock.

        r_w = 1;
        data_in = 4'b1111;
        #10;

        r_w = 0;
        #10;
        
        // Finaliza a simulação.
        $finish;
    end

    // Adicionar aqui monitoramento de sinais, se necessário.
    initial begin
        $monitor("At time %t, data_out = %b", $time, data_out);
    end

endmodule
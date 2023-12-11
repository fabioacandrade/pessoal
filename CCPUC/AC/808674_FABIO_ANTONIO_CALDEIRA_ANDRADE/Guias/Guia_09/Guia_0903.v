module clock ( output clk );
	reg clk;
	initial 
	begin
		clk = 1'b0;
	end
	always
	begin
		#12 clk = ~clk;
	end
endmodule

module pulse(output signal, input clock);
    reg [1:0] counter = 0; // Contador de 3 bits 
    reg signal;
    always @(posedge clock)
    begin
        if(counter < 2) signal = 1'b1; // Sinal alto para as primeiras 36 bordas positivas do relógio
        else signal = 1'b0;             // Sinal baixo para as próximas 36 bordas positivas
        if(counter == 2) counter = 0; // Reset contador depois de 72
        else counter = counter + 1;    // Incrementar contador
    end
endmodule

module Guia_0903;
    wire clk;
    clock clk_module(clk);
    wire pulse_signal;
    pulse pulse_module(pulse_signal, clk);
    
    initial
    begin
        $dumpfile("Guia_0903.vcd");
        $dumpvars(0, Guia_0903);
        #500 $finish;  // Simular por 500 unidades de tempo
    end
endmodule
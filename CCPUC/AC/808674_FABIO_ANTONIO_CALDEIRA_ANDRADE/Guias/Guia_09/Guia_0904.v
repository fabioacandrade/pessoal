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


module pulse(output reg signal, input clock);
    reg [1:0] count = 0;  // Contador de 2 bits para contar até 4

    always @(posedge clock)
    begin
        if(count < 3) 
        begin
        	#1;
            signal = ~signal; // Inverter o sinal a cada borda positiva até que o contador alcance 3
            count = count + 1;
        end
        else 
        begin
            count = 0;
            signal = ~signal;
        end
    end
endmodule


module Guia_0904;
    wire clock;
    clock clk(clock);
    wire signal;
    pulse pulse1(signal, clock);
    
    initial begin
        $dumpfile("Guia_0904.vcd");
        $dumpvars(0, Guia_0904);
        #500 $finish; // Simular por 500 unidades de tempo para observar vários ciclos.
    end
endmodule
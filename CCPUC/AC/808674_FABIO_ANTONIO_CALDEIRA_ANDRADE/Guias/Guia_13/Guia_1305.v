module tff ( output q, output qnot, input t, input clk, input preset, input clear );
    reg q, qnot;

 always @( posedge clk or ~preset or ~clear) begin
    if ( ~clear ) begin 
        q <= 0;
 		qnot <= 1;
 	end
    else
        if ( ~preset ) begin 
            q <= 1;
 			qnot <= 0; 
 		end
        else begin
            if ( t ) begin 
                q <= ~q; 
                qnot <= ~qnot; 
        	end
        end
    end
endmodule // tff

module contador_crescente_modulo9(output [3:0] contador, input clk, input clear);

    reg clear_reg = 1'b0;

    always@(posedge clk) begin
        if(contador[0] & ~contador[1] & ~contador[2] & contador[3]) begin
            clear_reg <= 1'b1;
     	end
     	else
     		clear_reg <= clear;
     end
    
    wire and1 = contador[1] & contador[0];

    wire and2 = and1 & contador[2]; 

    tff t1(contador[0], , 1'b1, clk, 1'b0, clear_reg);
    tff t2(contador[1], , contador[0], clk, 1'b0, clear_reg);
    tff t3(contador[2], , and1, clk, 1'b0, clear_reg);
    tff t4(contador[3], , and2, clk, 1'b0, clear_reg);


endmodule


module tb;

    reg clk = 0;
    reg clear = 1;
    wire [3:0] contador;
    
    contador_crescente_modulo9 tb0 (contador, clk, clear);

    always #5 clk = ~clk;

    initial begin

        #10;
        clear = 0;
        #250;

        $finish;
    end
    
    initial begin
        $monitor("Time = %0t, contador = %b", $time, contador);
    end

endmodule
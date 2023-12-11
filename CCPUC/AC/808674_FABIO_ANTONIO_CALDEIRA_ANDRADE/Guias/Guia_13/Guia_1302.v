// GUIA 1301

module jk_flip_flop ( output q, output qnot,
    input j, input k,
    input clk, input preset, input clear );

    reg q, qnot;

    always @( posedge clk or posedge preset or posedge clear ) begin
    if ( clear ) begin 
        q <= 0; qnot <= 1; 
    end
    else
        if ( preset ) begin 
            q <= 1; qnot <= 0; 
        end
        else
            if ( j & ~k ) begin 
                q <= 1; qnot <= 0; 
            end
            else
                if ( ~j & k ) begin 
                    q <= 0; qnot <= 1; 
                end
                else
                    if ( j & k ) begin 
                        q <= ~q; qnot <= ~qnot; 
                    end
    end
endmodule // jk_flip_flop

module contador_5bits_crescente(output [4:0] count, input clk, input clear);

    // 1 jk_flip_flop por bit de contador

    wire qnot1, qnot2, qnot3, qnot4;

    jk_flip_flop jk1(count[0], qnot1, 1'b1, 1'b1, clk, 1'b0, clear);
    jk_flip_flop jk2(count[1], qnot2, 1'b1, 1'b1, qnot1, 1'b0, clear);
    jk_flip_flop jk3(count[2], qnot3, 1'b1, 1'b1, qnot2, 1'b0, clear);
    jk_flip_flop jk4(count[3], qnot4, 1'b1, 1'b1, qnot3, 1'b0, clear);
    jk_flip_flop jk5(count[4],, 1'b1, 1'b1, qnot4, 1'b0, clear);    
    

endmodule


module contador_5bit_crescente_tb;
    reg clk = 0;
    reg clear = 0;
    wire [4:0] contador;

    contador_5bits_crescente uut (contador, clk, clear);
    
    always #5 clk = ~clk; 

    // Test sequence
    initial begin
    
        // Reset sequence
        clear = 1;  
        #10;        
        clear = 0;  
        #315;       

        $finish;   
    end

    initial begin	
        $monitor("Time = %0t, Contador: %b", $time, contador);
    end
endmodule
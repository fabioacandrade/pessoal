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

module contador_decadico_crescente_4bit(output [3:0] count, input clk, input clear);

    reg clr;

    always @(posedge clk) begin
        if(count[0] & ~count[1] & ~count[2] & count[3])begin
            clr = 1;
        end
        else
            clr = clear;
    end
    

    jk_flip_flop ff1(count[0], , 1'b1, 1'b1, clk, 1'b0, clr);
    jk_flip_flop ff2(count[1], , 1'b1, 1'b1, ~count[0], 1'b0, clr);
    jk_flip_flop ff3(count[2], , 1'b1, 1'b1, ~count[1], 1'b0, clr);
    jk_flip_flop ff4(count[3], , 1'b1, 1'b1, ~count[2], 1'b0, clr);


endmodule

module contador_4bit_crescente_decadico;
    reg clk = 0;
    reg clear = 0;
    wire [3:0] contador;

    contador_decadico_crescente_4bit uut (contador, clk, clear);
   
    always #5 clk = ~clk; 

    // Test sequence
    initial begin
    
        // Reset sequence
        clear = 1;  
        #10;        
        clear = 0;  
        #295;       

        $finish;   
    end

    initial begin	
        $monitor("Time = %0t, Contador: %b", $time, contador);
    end
endmodule
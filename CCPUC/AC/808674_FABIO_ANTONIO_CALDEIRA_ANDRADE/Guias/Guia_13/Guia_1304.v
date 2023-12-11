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

module contador_decadico_decrescente_4bit(output [3:0] count, input clk,  input set);
    
    reg s = 1'b0;

    always@(posedge clk) begin
        if(~count[3] & count[2] & count[1] & ~count[0])begin
            s = 1;
        end
        else
            s = set;
    end

    jk_flip_flop jk1(count[0], , 1'b1, 1'b1, clk, s, clear);
    jk_flip_flop jk2(count[1], , 1'b1, 1'b1, count[0], s, clear);
    jk_flip_flop jk3(count[2], , 1'b1, 1'b1, count[1], s, clear);
    jk_flip_flop jk4(count[3], , 1'b1, 1'b1, count[2], s, clear);

endmodule

module contador_4bit_decrescente_decadico;
    reg clk = 0;
    reg preset = 1;
    wire [3:0] contador;

    contador_decadico_decrescente_4bit uut (contador, clk, preset);
   
    always #5 clk = ~clk; 

    // Test sequence
    initial begin
    
        // Reset sequence 
        #10;      
        preset = 0;  
        #295;       

        $finish;   
    end

    initial begin	
        $monitor("Time = %0t, Contador: %b", $time, contador);
    end
endmodule
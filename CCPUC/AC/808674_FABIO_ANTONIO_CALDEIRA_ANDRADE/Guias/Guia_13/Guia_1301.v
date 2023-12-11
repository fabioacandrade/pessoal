module jkff ( output q_out, output qnot_out,
    input j_in, input k_in,
    input clk_in, input preset_in, input clear_in );

    reg q_out, qnot_out;

    always @( posedge clk_in or posedge preset_in or posedge clear_in ) begin
    if ( clear_in ) begin 
        q_out <= 0; qnot_out <= 1; 
    end
    else
        if ( preset_in ) begin 
            q_out <= 1; qnot_out <= 0; 
        end
        else
            if ( j_in & ~k_in ) begin 
                q_out <= 1; qnot_out <= 0; 
            end
            else
                if ( ~j_in & k_in ) begin 
                    q_out <= 0; qnot_out <= 1; 
                end
                else
                    if ( j_in & k_in ) begin 
                        q_out <= ~q_out; qnot_out <= ~qnot_out; 
                    end
    end
endmodule // jkff

module contador_5bits_decrescente(output [4:0] count_out, input clk_in, input clear_in);

    //1 jk por bit de contador

    jkff jk1(count_out[0], , 1'b1, 1'b1, clk_in, 1'b0, clear_in);
    jkff jk2(count_out[1], , 1'b1, 1'b1, count_out[0], 1'b0, clear_in);
    jkff jk3(count_out[2], , 1'b1, 1'b1, count_out[1], 1'b0, clear_in);
    jkff jk4(count_out[3], , 1'b1, 1'b1, count_out[2], 1'b0, clear_in);
    jkff jk5(count_out[4], , 1'b1, 1'b1, count_out[3], 1'b0, clear_in);
  
endmodule


module contador_5bit_decrescente_tb;
    reg clk_in = 0;
    reg clear_in = 0;
    wire [4:0] contador_out;

    contador_5bits_decrescente uut (contador_out, clk_in, clear_in);
    
    always #5 clk_in = ~clk_in; 

    // Test sequence
    initial begin
    
        // Reset sequence
        clear_in = 1;  
        #10;        
        clear_in = 0;  
        #325;       

        $finish;   
    end

    // Monitor changes and display them
    initial begin	
        $monitor("Time = %0t, Contador: %b", $time, contador_out);
    end
endmodule
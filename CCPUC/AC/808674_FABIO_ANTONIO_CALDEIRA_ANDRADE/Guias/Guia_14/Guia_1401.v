module dff ( output q_out, output qnot_out,
    input d_in, input clk_in,
    input preset_in, input clear_in );

        reg q_out, qnot_out;
        always @( posedge clk_in ) begin
            if ( clear_in ) begin 
                q_out <= 0; qnot_out <= 1; 
            end
            else
                if ( preset_in ) begin 
                    q_out <= 1; qnot_out <= 0; 
                end
                else begin 
                    q_out <= d_in; qnot_out <= ~d_in; 
                end
        end
endmodule // dff

module left_shift ( output [5:0] number_out, input bit1_in, input load_in, input clk_in, input clr_in);

    wire ld = load_in & bit1_in;

    dff d1(number_out[0], , 1'b0,      clk_in, ld,   clr_in);
    dff d2(number_out[1], , number_out[0], clk_in, 1'b0, clr_in);
    dff d3(number_out[2], , number_out[1], clk_in, 1'b0, clr_in);
    dff d4(number_out[3], , number_out[2], clk_in, 1'b0, clr_in);
    dff d5(number_out[4], , number_out[3], clk_in, 1'b0, clr_in);
    dff d6(number_out[5], , number_out[4], clk_in, 1'b0, clr_in);
    
    
endmodule // left_shift	


module lefet_shift_tb;

    reg clk = 0;
    reg clear = 0;
    reg bit1 = 0;
   	reg load = 0;
    wire [5:0] number_out;

    left_shift tbu (number_out, bit1, load, clk, clear);
    
    always #5 clk = ~clk; 

    // Test sequence
    initial begin
        // Reset sequence
        clear = 1;
        #10;
        clear = 0;
        bit1 = 1;
        load = 1;
       	#10;
       	load = 0;
       	#100;
                 
        $finish;   
    end

    // Monitor changes and display them
    initial begin	
        $monitor("Time = %0t, Output: %b", $time, number_out);
    end
endmodule
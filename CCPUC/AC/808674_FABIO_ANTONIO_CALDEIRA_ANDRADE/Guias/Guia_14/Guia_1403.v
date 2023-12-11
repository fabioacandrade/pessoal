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

module right_shift_ring ( output [5:0] number_out, input dataInput_in, input load_in, input clk_in, input clr_in );


	dff d0 (number_out[0], , number_out[5], clk_in, 1'b0, clr_in );
	dff d1 (number_out[1], , number_out[0], clk_in, 1'b0, clr_in );
	dff d2 (number_out[2], , number_out[1], clk_in, 1'b0, clr_in );
	dff d3 (number_out[3], , number_out[2], clk_in, 1'b0, clr_in );
	dff d4 (number_out[4], , number_out[3], clk_in, 1'b0, clr_in );
	dff d5 (number_out[5], , load_in ? dataInput_in : number_out[4]     , clk_in, 1'b0, clr_in );
		
	
endmodule // right_ring


module right_shift_tb;

    reg clk = 0;
    reg clear = 0;
    reg load = 0;
    reg data = 0;
    wire [5:0] number_out;

	right_shift_ring tbu (number_out, data, load, clk, clear);
    
    always #5 clk = ~clk; 	

    // Test sequence
    initial begin
        // Reset sequence
        #5;
        clear = 1;
        #10;
        clear = 0;
        load = 1;
        data = 1;
        #5;
        load = 0;
        #170;                 
        $finish;   
    end

    // Monitor changes and display them
    initial begin	
        $monitor("Time = %0t, Output: %b", $time, number_out);
    end
endmodule
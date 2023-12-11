// 808674 - Fábio Andrade

	module comparador(input a, input b, output s);
		xor XOR_GATE(s,a,b);
	endmodule

	module test;

		reg  [0:0] result;

		reg  [5:0] A;
		reg  [5:0] B;

		reg  [5:0] C;
		reg  [5:0] D;

		wire [5:0] S1;
		wire [5:0] S2;

		initial begin
	
		 
		A = 6'b100111;
		B = 6'b100111;
		

		C = 6'b101010;
		D = 6'b010101;
		
		end
		
	    genvar i;
        generate 
            for (i = 0; i < 6; i = i + 1) begin
                comparador comp1(A[i], B[i], S1[i]);
                comparador comp2(C[i], D[i], S2[i]);
            end
        endgenerate

        initial begin
            $display("-------------- TESTE COMPARADOR-6-BITS ------------");
            $display("A = 100111,  B = 100111");
            #1;
			if(S1 == 6'b000000)begin
				$display("Os números são identicos");
			end
			else begin
				$display("Os números são distintos");
			end

            $display("C = 101010,  D = 010101");
            #1;
			if(S2 == 6'b000000)begin
				$display("Os números são identicos");
			end
			else begin
				$display("Os números são distintos");
			end            
        end
		
	endmodule
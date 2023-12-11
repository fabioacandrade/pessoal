// 808674 - Fábio Andrade
	
    module Half_Subtractor(input a, input b, output diff, output borrow);
        xor (diff, a, b);
        and (borrow, ~a, b);
    endmodule
    

    module Full_Subtractor(input a, input b, input bin, output diff, output bo);
        wire s1, b1, b2;
        Half_Subtractor hs0(a, b, s1, b1);
        Half_Subtractor hs1(s1, bin, diff, b2);
        or (bo, b1, b2);
    endmodule 
    

    module Teste;
        reg  [5:0] A;
        reg  [5:0] B;
        wire [5:0] S;
        wire [5:0] c;
    
      
        initial begin
            A = 6'b111111;
            B = 6'b010101;
        end
    
        Full_Subtractor fs0(A[0], B[0], 1'b0, S[0], c[0]);    
    
        genvar i;
        generate 
            for (i = 1; i < 6; i = i + 1) begin
                Full_Subtractor fs(A[i], B[i], c[i-1], S[i], c[i]);
            end
        endgenerate
   
    

        initial begin
            $display("A = %b", A);
            $display("B = %b", B);
            $display("S = %b", S);
        end
    endmodule
    
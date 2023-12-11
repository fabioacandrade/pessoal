
// 808674 - Fábio Andrade
	
    module Half_Adder(input a, input b, output sum, output carry);
        xor (sum, a, b);
        and (carry, a, b);
    endmodule
    
    module Full_Adder(input a, input b, input cin, output sum, output co);
        wire s1, c1, c2;
        Half_Adder ha0(a, b, s1, c1);
        Half_Adder ha1(s1, cin, sum, c2);
        or (co, c1, c2);
    endmodule 
    
    module Teste;
        reg  [4:0] A;
        reg  [4:0] B;
        wire [5:0] S;
        wire [4:0] c;
    

        initial begin
            A = 5'b11111;
            B = 5'b01010;
        end
    
    
        Full_Adder fa0(A[0], B[0], 1'b0, S[0], c[0]);    
    
        
        genvar i;
        generate 
            for (i = 1; i < 5; i = i + 1) begin
                Full_Adder fa(A[i], B[i], c[i-1], S[i], c[i]);
            end
        endgenerate
    
        
        assign S[5] = c[4];
    
        
        initial begin
            $display("A = %b", A);
            $display("B = %b", B);
            $display("S = %b", S);
        end
    endmodule
    
// 808674 - Fábio Andrade


module c1(input num, output num1);
    not NOT_gate(num1,num);
endmodule

module c2(input [5:0] num1, output [6:0] num2);
    assign num2 = num1 + 1;
endmodule

module test;
    reg  [5:0] A;
    wire [5:0] A1;
    wire [6:0] A2;
	
    initial begin
        A = 6'b100111;
    end

    genvar i;
    generate 
        for (i = 0; i < 6; i = i + 1) begin
            c1 complemento(A[i], A1[i]);
        end
    endgenerate
    
    c2 complemento2(A1,A2);
    
    initial begin
        $display("--------- TESTE PARA COMPLEMENTO DE 2 ------------");
        #1;
        $display("O complemento de 2 para o número 100111 = %b",A2);

    end
endmodule
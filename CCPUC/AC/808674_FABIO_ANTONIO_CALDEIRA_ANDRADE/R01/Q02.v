module Q02 (output s, input a, input b, input c);
  wire w1, w2, w3, w4;
  
  not NOT_1 (w1, b);
  not NOT_2 (w2, c);
  
  and AND_1 (w3, a, w2);
  and AND_2 (w4, a, w1, c);
  
  or OR_1 (s, w3, w4);
  
endmodule 

module test_Q02;
    reg a, b, c;
    wire s;

    Q02 UUT (
        .a(a),
        .b(b),
        .c(c),
        .s(s)
    );

    initial begin
        a = 0; b = 0; c = 0;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 0; b = 0; c = 1;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 0; b = 1; c = 0; 
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 0; b = 1; c = 1;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 1; b = 0; c = 0;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 1; b = 0; c = 1;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 1; b = 1; c = 0;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);
        a = 1; b = 1; c = 1;
        #10;
        $display("a = %b, b = %b, c = %b, s = %b", a, b, c, s);

        $finish;
    end
    

  
endmodule

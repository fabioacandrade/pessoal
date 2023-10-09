module Q01d (input a,input b,input c,input d,output s);
    assign s = (b|c) & (a|~b|~c) & (~a|~b|d);

endmodule

module test_Q01d;
    reg a, b, c, d;
    wire s;

    Q01d UUT (
        .a(a),
        .b(b),
        .c(c),
        .d(d),
        .s(s)
    );

    initial begin
        a = 0; b = 0; c = 0; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 0; c = 0; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 0; c = 1; d = 0; 
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 0; c = 1; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 1; c = 0; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 1; c = 0; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 1; c = 1; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 0; b = 1; c = 1; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 0; c = 0; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 0; c = 0; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 0; c = 1; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 0; c = 1; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 1; c = 0; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 1; c = 0; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 1; c = 1; d = 0;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        a = 1; b = 1; c = 1; d = 1;
        #10;
        $display("a = %b, b = %b, c = %b, d = %b, s = %b", a, b, c, d, s);
        $finish;
    end
endmodule

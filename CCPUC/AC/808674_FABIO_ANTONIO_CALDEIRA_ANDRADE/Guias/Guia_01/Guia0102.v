/*
 Guia_0102.v
 808674 - Fábio Antônio Caldeira Andrade
*/
module Guia_0102;
// define data
 integer x = 0; // decimal
 reg [7:0] a = 8'b0010110; // binary (bits - little endian)
 reg [7:0] b = 8'b0011011; // binary (bits - little endian)
 reg [7:0] c = 8'b0100100; // binary (bits - little endian)
 reg [7:0] d = 8'b0101001; // binary (bits - little endian)
 reg [7:0] e = 8'b0110111; // binary (bits - little endian)

// actions
 initial
 begin : main
 $display ( "Guia_0102 - Tests" );
 x = a;
 $display ( "a = %d" , x );
 x=b;
 $display ( "b = %d" , x );
 x=c;
 $display ( "c = %d" , x );
 x=d;
 $display ( "d = %d" , x );
 x=e;
 $display ( "e = %d" , x );
 
 end // main
endmodule // Guia_0102
/*
 Guia_0103.v
 808674 - Fábio Antônio Caldeira Andrade
*/
module Guia_0103;
// define data
 integer a = 61; // decimal
 integer b1 = 54; // decimal
 integer c = 77; // decimal
 integer d = 151; // decimal
 integer e = 738; // decimal

 reg [7:0] b = 0; // binary
// actions
 initial
 begin : main
 $display ( "Guia_0103" );
 $display ( "a = %d" , a );
 b = a;
 $display ( "a = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
 $display ( "b = %d" , b1 );
 b = b1;
 $display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
 $display ( "c = %d" , c );
 b = c;
 $display ( "c = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
 $display ( "d = %d" , d );
 b = d;
 $display ( "d = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
 $display ( "e = %d" , e );
 b = e;
 $display ( "e = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
 end // main
endmodule // Guia_0103
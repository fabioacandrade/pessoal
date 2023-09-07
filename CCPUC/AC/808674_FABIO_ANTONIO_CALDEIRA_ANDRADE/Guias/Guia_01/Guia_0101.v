/*
 Guia_0101.v
 808674-Fábio Antonio Caldeira Andrade
*/
module Guia_0101;
// define data
 integer a = 26; // decimal
 integer b2 = 53; // decimal
 integer c = 723; // decimal
 integer d = 312; // decimal
 integer e = 365; // decimal
 reg [7:0] b = 0; // binary (bits - little endian)
// actions
 initial
 begin : main
 $display ( "Guia_0101" );
 $display ( "a(10) = %d" , a );
 b = a;
 $display ( "a(2) = %8b", b );
 $display ( "b(10) = %d" , b2 );
 b = b2;
 $display ( "b(2) = %8b", b );
 $display ( "c(10) = %d" , c );
 b = c;
 $display ( "c(2) = %8b", b );
 $display ( "d(10) = %d" , d );
 b = d;
 $display ( "d(2) = %8b", b );
 $display ( "e(10) = %d" , e );
 b = e;
 $display ( "e(2) = %8b", b );
 
 end // main
endmodule // Guia_0101

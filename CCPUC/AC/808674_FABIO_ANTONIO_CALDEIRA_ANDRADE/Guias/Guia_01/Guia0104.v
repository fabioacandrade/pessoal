/*
 Guia_0104.v
 808674 - Fábio Antônio Caldeira Andrade
*/
module Guia_0104;
// define data 
 reg [7:0] b = 8'b1101111; // binary
// actions
 initial
 begin : main
 $display ( "Guia_0104" );
 $display ( "decimal = %d" , b );
 $display ( "binario = %8b", b );
 $display ( "base 4 = %d%d%d%d", b[7:6], b[5:4], b[3:2], b[1:0]);
 $display ( "hexa = %h", b);
 $display("Octal = %o", b);

 end // main
endmodule // Guia_0104
/*
 Guia0105.v
 808674-Fábio Antônio Caldeira Andrade
*/

module Guia_0105b;
// define data
reg [0:8][7:0] s = "PUC-Minas";

// actions
initial
begin : main
    $display("s = %s", s);

    for (integer i = 0; i < 9; i++)
    begin
        $display("%c=%h", s[i], s[i]);
    end
end // main

endmodule // Guia0105
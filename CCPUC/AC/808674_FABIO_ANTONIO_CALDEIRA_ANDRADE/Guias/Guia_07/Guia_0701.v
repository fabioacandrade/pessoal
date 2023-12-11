//808674 - Fábio Andrade
module lu_with_selection (
  output wire out1,
  output wire out2,
  input wire a,
  input wire b,
  input wire select
);
  // Define local wires
  wire and_result;
  wire nand_result;

  // AND gate
  and AND_gate (
    and_result,
    a,
    b
  );

  // NAND gate
  nand NAND_gate (
    nand_result,
    a,
    b
  );

  // Output selection
  assign out1 = (select) ? and_result : nand_result;
  assign out2 = and_result;

endmodule // lu_with_selection

// -------------------------
// test_lu_with_selection
// -------------------------
module test_lu_with_selection;
  // Define data
  reg x;
  reg y;
  reg s;
  wire w1;
  wire w2;

  // Instantiate the LU module
  lu_with_selection lu_mod (
    w1,
    w2,
    x,
    y,
    s
  );

  // Main part
  initial begin : main
    $display("808674 - Fábio Andrade");
    $display("Test Logical Unit with Selection");
    $display(" x y s out1 out2");
    x = 1'b0; y = 1'b1; s = 1'b0;

    // Project tests for the module
    #1 $monitor("%4b %4b %4b %4b %4b", x, y, s, w1, w2);
    #1 s = 1'b1;
  end

endmodule // test_lu_with_selection

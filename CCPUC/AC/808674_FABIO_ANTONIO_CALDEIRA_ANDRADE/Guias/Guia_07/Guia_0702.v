// -------------------------
// Guia_0702 - Logical Unit
// Nome: Fábio Andrade
// Matricula: 808674
// -------------------------

// -------------------------
// lu_with_selection
// -------------------------
module lu_with_selection (
  output wire out,
  input wire a,
  input wire b,
  input wire select
);
  // Define local wires
  wire or_result;
  wire nor_result;

  // OR gate
  or OR_gate (
    or_result,
    a,
    b
  );

  // NOR gate
  nor NOR_gate (
    nor_result,
    a,
    b
  );

  // Output selection
  assign out = (select) ? nor_result : or_result;

endmodule // lu_with_selection

// -------------------------
// test_lu_with_selection
// -------------------------
module test_lu_with_selection;
  // Define data
  reg x;
  reg y;
  reg s;
  wire w;

  // Instantiate the LU module
  lu_with_selection lu_mod (
    w,
    x,
    y,
    s
  );

  // Main part
  initial begin : main
    $display("Guia_0702 - Fábio Andrade - 808674");
    $display("Test Logical Unit with Selection");
    $display(" x y s out");
    x = 1'b0; y = 1'b1; s = 1'b0;

    // Project tests for the module
    #1 $monitor("%4b %4b %4b %4b", x, y, s, w);
    #1 s = 1'b1;
  end

endmodule // test_lu_with_selection

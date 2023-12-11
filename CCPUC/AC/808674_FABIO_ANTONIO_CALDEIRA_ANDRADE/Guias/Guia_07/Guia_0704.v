// -------------------------
// Guia_0704 - Unidade Lógica
// Nome: Fábio Andrade
// Matrícula: 808674
// -------------------------

module lu_com_selecao (
  output wire out,
  input wire a,
  input wire b,
  input wire sel_xor_xnor_or_nor
);
  // Define fios locais
  wire xor_result, xnor_result, or_result, nor_result;
  wire resultado_grupo;

  // Porta XOR
  xor porta_XOR (
    xor_result,
    a,
    b
  );

  // Porta XNOR
  xnor porta_XNOR (
    xnor_result,
    a,
    b
  );

  // Porta OR
  or porta_OR (
    or_result,
    a,
    b
  );

  // Porta NOR
  nor porta_NOR (
    nor_result,
    a,
    b
  );

  // Seleciona o resultado do grupo
  assign resultado_grupo = (sel_xor_xnor_or_nor == 2'b00) ? xor_result :
                       (sel_xor_xnor_or_nor == 2'b01) ? xnor_result :
                       (sel_xor_xnor_or_nor == 2'b10) ? or_result : nor_result;

  // Saída
  assign out = resultado_grupo;

endmodule // lu_com_selecao

// -------------------------
// test_lu_com_selecao
// -------------------------
module test_lu_com_selecao;
  // Define dados
  reg x;
  reg y;
  reg sel_xor_xnor_or_nor;
  wire w;

  // Instancia o módulo LU
  lu_com_selecao lu_mod (
    w,
    x,
    y,
    sel_xor_xnor_or_nor
  );

  // Parte principal
  initial begin : main
    $display("Guia_0704 - Fábio Andrade - 808674");
    $display("Teste da Unidade Lógica com Seleção");
    $display(" x y sel_xor_xnor_or_nor out");
    x = 1'b0; y = 1'b1; sel_xor_xnor_or_nor = 2'b00;

    // Projeta testes para o módulo
    #1 $monitor("%4b %4b %4b %4b", x, y, sel_xor_xnor_or_nor, w);
    #1 sel_xor_xnor_or_nor = 2'b01;
  end

endmodule // test_lu_com_selecao

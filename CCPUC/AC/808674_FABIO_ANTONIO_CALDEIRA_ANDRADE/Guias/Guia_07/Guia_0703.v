// -------------------------
// Guia_0703 - Unidade Lógica
// Nome: Fábio Andrade
// Matrícula: 808674
// -------------------------

module lu_com_selecao (
  output wire out,
  input wire a,
  input wire b,
  input wire sel_grupo,
  input wire sel_porta
);
  // Define fios locais
  wire and_result, nand_result, or_result, nor_result;
  wire resultado_grupo, resultado_final;

  // Porta AND
  and porta_AND (
    and_result,
    a,
    b
  );

  // Porta NAND
  nand porta_NAND (
    nand_result,
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
  assign resultado_grupo = (sel_grupo) ? nand_result | nor_result : and_result | or_result;

  // Seleciona o resultado final
  assign resultado_final = (sel_porta) ? resultado_grupo : and_result | or_result;

  // Saída
  assign out = resultado_final;

endmodule // lu_com_selecao

// -------------------------
// test_lu_com_selecao
// -------------------------
module test_lu_com_selecao;
  // Define dados
  reg x;
  reg y;
  reg sel_grupo;
  reg sel_porta;
  wire w;

  // Instancia o módulo LU
  lu_com_selecao lu_mod (
    w,
    x,
    y,
    sel_grupo,
    sel_porta
  );

  // Parte principal
  initial begin : main
    $display("Guia_0703 - Fábio Andrade - 808674");
    $display("Teste da Unidade Lógica com Seleção");
    $display(" x y sel_grupo sel_porta out");
    x = 1'b0; y = 1'b1; sel_grupo = 1'b0; sel_porta = 1'b0;

    // Projeta testes para o módulo
    #1 $monitor("%4b %4b %4b %4b %4b", x, y, sel_grupo, sel_porta, w);
    #1 sel_grupo = 1'b1;
    #1 sel_porta = 1'b1;
  end

endmodule // test_lu_com_selecao

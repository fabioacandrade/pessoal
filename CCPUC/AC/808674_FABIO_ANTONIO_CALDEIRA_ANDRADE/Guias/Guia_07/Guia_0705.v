// -------------------------
// Guia_0705 - Unidade Lógica
// Nome: Fábio Andrade
// Matrícula: 808674
// -------------------------

module lu_com_selecao (
  output wire out,
  input wire a,
  input wire b,
  input wire[2:0] sel_operacao
);
  // Define fios locais
  wire not_result, and_result, nand_result, xor_result, xnor_result, or_result, nor_result;
  wire resultado_grupo;

  // Porta NOT
  not porta_NOT (
    not_result,
    a
  );

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
  always @* begin
    case(sel_operacao)
      3'b000: resultado_grupo = not_result;
      3'b001: resultado_grupo = and_result;
      3'b010: resultado_grupo = nand_result;
      3'b011: resultado_grupo = xor_result;
      3'b100: resultado_grupo = xnor_result;
      3'b101: resultado_grupo = or_result;
      3'b110: resultado_grupo = nor_result;
      default: resultado_grupo = 1'b0; // Valor padrão caso a seleção não seja reconhecida
    endcase
  end

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
  reg[2:0] sel_operacao;
  wire w;

  // Instancia o módulo LU
  lu_com_selecao lu_mod (
    w,
    x,
    y,
    sel_operacao
  );

  // Parte principal
  initial begin : main
    $display("Guia_0705 - Fábio Andrade - 808674");
    $display("Teste da Unidade Lógica com Seleção");
    $display(" x y sel_operacao out");
    x = 1'b0; y = 1'b1; sel_operacao = 3'b000;

    // Projeta testes para o módulo
    #1 $monitor("%4b %4b %b %4b", x, y, sel_operacao, w);
    #1 sel_operacao = 3'b011;
  end

endmodule // test_lu_com_selecao

// 06.) Projetar e descrever em Verilog um módulo
// gerador de pulso (pulse) com marcação igual
// a 4 unidades de tempo, sincronizado com a
// borda de descida do gerador do Guia_0900.v.
// O nome do arquivo deverá ser Guia_0906.v.
// Incluir previsão de testes e verificação da
// carta de tempo usando GTKWave.
// DICA: Usar always @(negedge clk).

module Guia_0906(
    input clk,
    input rst,
    output reg pulse
    );

    always @(negedge clk) begin
        if (rst) begin
            pulse <= 0;
        end else begin
            pulse <= 1;
        end
    end

endmodule


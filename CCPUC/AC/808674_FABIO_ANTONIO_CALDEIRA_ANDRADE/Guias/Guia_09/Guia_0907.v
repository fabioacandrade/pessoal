// 07.) Projetar e descrever em Verilog um módulo
// gerador de pulso (pulse) com marcação igual
// a 5 unidades de tempo, sincronizado com
// o nível alto e estável do gerador do Guia_0900.v.
// O nome do arquivo deverá ser Guia_0907.v.
// Incluir previsão de testes e verificação da
// carta de tempo usando GTKWave.
// DICA: Usar always @(clk).


module Guia_0907(
    input clk,
    input rst,
    output reg pulse
    );

    always @(clk) begin
        if (rst) begin
            pulse <= 0;
        end else begin
            pulse <= 1;
        end
    end

endmodule
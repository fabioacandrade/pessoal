// Projetar e descrever em Verilog um módulo
// gerador de pulso (pulse) com marcação igual
// a 2 unidades de tempo, sincronizado com a
// borda de subida do gerador do Guia_09001.v.
// O nome do arquivo deverá ser Guia_0905.v.
// Incluir previsão de testes e verificação da
// carta de tempo usando GTKWave.
// DICA: Usar always @(posedge clk).

module Guia_0905(
    input clk,
    input rst,
    output reg pulse
    );

    always @(posedge clk) begin
        if (rst) begin
            pulse <= 0;
        end else begin
            pulse <= 1;
        end
    end

endmodule
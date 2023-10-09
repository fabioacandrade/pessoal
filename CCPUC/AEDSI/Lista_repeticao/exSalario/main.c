#include <stdio.h>

int main() {
    float horasTrabalhadas, salarioMinimo, horasExtras, valorHora, valorHoraExtra, salarioBruto, valorHoraExtraTotal, salarioTotal;

    // Recebe as informações do empregado
    printf("Digite o número de horas trabalhadas: ");
    scanf("%f", &horasTrabalhadas);

    printf("Digite o valor do salário mínimo: ");
    scanf("%f", &salarioMinimo);

    printf("Digite o número de horas extras trabalhadas: ");
    scanf("%f", &horasExtras);

    // Calcula os valores necessários
    valorHora = salarioMinimo / 8;
    valorHoraExtra = salarioMinimo / 4;
    salarioBruto = horasTrabalhadas * valorHora;
    valorHoraExtraTotal = horasExtras * valorHoraExtra;
    salarioTotal = salarioBruto + valorHoraExtraTotal;

    // Mostra o salário a receber
    printf("Salário a receber: R$ %.2f\n", salarioTotal);

    return 0;
}


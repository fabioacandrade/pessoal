#include <stdio.h>

int main() {
    float horasTrabalhadas, salarioMinimo, horasExtras, valorHora, valorHoraExtra, salarioBruto, valorHoraExtraTotal, salarioTotal;

    // Recebe as informa��es do empregado
    printf("Digite o n�mero de horas trabalhadas: ");
    scanf("%f", &horasTrabalhadas);

    printf("Digite o valor do sal�rio m�nimo: ");
    scanf("%f", &salarioMinimo);

    printf("Digite o n�mero de horas extras trabalhadas: ");
    scanf("%f", &horasExtras);

    // Calcula os valores necess�rios
    valorHora = salarioMinimo / 8;
    valorHoraExtra = salarioMinimo / 4;
    salarioBruto = horasTrabalhadas * valorHora;
    valorHoraExtraTotal = horasExtras * valorHoraExtra;
    salarioTotal = salarioBruto + valorHoraExtraTotal;

    // Mostra o sal�rio a receber
    printf("Sal�rio a receber: R$ %.2f\n", salarioTotal);

    return 0;
}


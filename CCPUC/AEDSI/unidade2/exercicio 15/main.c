#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{   float horast,smin,horasex,salariobruto,salario,qthrex;
    float valorhorat,valorhoraex;
    puts("digite o numero de horas trabalhadas, o salario minimo atual e o numero de horas extras:");
    scanf("%f %f %f",&horast, &smin,&horasex);
    valorhorat= smin*1/8;
    valorhoraex=smin*1/4;
    salariobruto=horast*valorhorat;
    qthrex=valorhoraex*horasex;
    salario=salariobruto+qthrex;
    printf("O salario recebido eh de : R$ %.2f", salario);
    return 0;
}

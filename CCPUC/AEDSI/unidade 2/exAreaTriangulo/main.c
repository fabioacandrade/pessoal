#include <stdio.h>
#include <stdlib.h>

int main()
{   float base,altura;
    puts("BEM VINDO AO CALCULADOR DA AREA DE UM TRIANGULO\n");
    puts("digite o valor da base em metros:");
    scanf("%f",&base);
    puts("digite o valor da altura em metros:");
    scanf("%f",&altura);
    printf("A AREA DO SEU TRIANGULO EH: %2.2f m^2 \n",base*altura/2);
    puts("OBRIGADO POR UTILIZAR NOSSO ALGORITMO :) ");


    return 0;
}

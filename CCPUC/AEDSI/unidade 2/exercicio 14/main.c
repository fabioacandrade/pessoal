#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{   float produto,desconto,aumento;
    puts("digite o preco do produto:");
    scanf("%f",&produto);
    desconto= produto-produto*0.1;
    aumento=produto+produto*0.2;
    printf("o desconto eh de: %.2f \ne o aumento de: %.2f",desconto,aumento);
    return 0;
}

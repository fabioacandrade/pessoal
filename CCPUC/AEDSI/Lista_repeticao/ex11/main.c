#include <stdio.h>
#include <stdlib.h>

int main()
{
    /*Enquanto o valor -1 não for digitado, leia números fornecidos pelo usuário. No final, exiba quantos números
foram digitados e quantas vezes o número 5 foi informado.*/
    int num,contador=0,contador5=0;
    puts("digite um numero ou -1 para parar:");
    scanf("%d",&num);
    while(num!=-1){
        contador++;
        if(num==5)
            contador5++;
        puts("digite um numero ou -1 para parar:");
        scanf("%d",&num);
    }
        printf("foram digitados %d numeros e %d vezes o numero 5",contador,contador5);


    return 0;
}

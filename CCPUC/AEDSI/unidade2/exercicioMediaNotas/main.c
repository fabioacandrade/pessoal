#include <stdio.h>
#include <stdlib.h>

int main()
{   float linguagens, humanas, naturezas, matematica, redacao;
    char nome[40];
    puts("BEM VINDO AO CALCULADOR DA NOTA DO ENEM \n DIGITE O NOME DO ALUNO:");
    fgets(nome,sizeof(nome),stdin);
    puts("DIGITE A NOTA DE MATEMATICA:");
    scanf("%f",&matematica);
    puts("DIGITE A NOTA DE LINGUAGENS:");
    scanf("%f",&linguagens);
    puts("DIGITE A NOTA DE HUMANAS:");
    scanf("%f",&humanas);
    puts("DIGITE A NOTA DE NATUREZAS:");
    scanf("%f",&naturezas);
    puts("DIGITE A NOTA DE REDACAO:");
    scanf("%f",&redacao);

    printf("%s:%2.2f",nome,(matematica+humanas+naturezas+redacao+linguagens)/5);


    return 0;
}

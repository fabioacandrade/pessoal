#include <stdio.h>
#include <stdlib.h>

int main()
{
    //Ler 30 números inteiros e exibir a soma dos números divisíveis por 5.
    int soma=0,num;
    for(int i=0;i<5;i++){
        printf("digite o %d numero:",i+1);
        scanf("%d",&num);
        if(num%5==0)
            soma+=num;
    }
    printf("a soma dos divisiveis por 5 eh:%d",soma);
    return 0;
}

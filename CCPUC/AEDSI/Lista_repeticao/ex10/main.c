#include <stdio.h>
#include <stdlib.h>

int main()
{   /*Ler N n�meros inteiros e exibir o produto dos n�meros pares. A quantidade N de n�meros a serem lidos �
    informada pelo usu�rio.*/
    int num,produto=1,qnt;
    puts("digite a quantidade:");
    scanf("%d",&qnt);
    for(int i=0;i<qnt;i++){
        printf("digite o %d numero:",i+1);
        scanf("%d",&num);
        if(num%2==0){
            produto*=num;
        }
    }
        printf("o produto dos pares eh:%d",produto);

    return 0;
}

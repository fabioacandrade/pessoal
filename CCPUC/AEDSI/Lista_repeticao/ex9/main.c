#include <stdio.h>
#include <stdlib.h>

int main()
{
    //Ler 100 n�meros inteiros e exibir o produto dos n�meros.
    int p=1,num;
    for(int i=0;i<100;i++){
        printf("digite o %d numero:",i+1);
        scanf("%d",&num);
        p*=num;
    }
    printf("%d",p);

    return 0;
}

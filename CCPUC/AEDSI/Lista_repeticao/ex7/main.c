#include <stdio.h>
#include <stdlib.h>

int main()
{
    int soma=0,n,num;
    puts("digite a quantidade:");
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        printf("digite o %d numero:",i+1);
        scanf("%d",&num);
        if(num%2==1)
            soma+=num;
    }
    printf("a soma dos impares eh:%d",soma);
    return 0;
}

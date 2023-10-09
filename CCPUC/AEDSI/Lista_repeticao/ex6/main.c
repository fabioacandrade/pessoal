#include <stdio.h>
#include <stdlib.h>

int main()
{
    int soma=0,num;
    for(int i=0;i<100;i++){
    printf("digite o %d numero:",i+1);
    scanf("%d",&num);
    soma+=num;
    }
    printf("%d",soma);
    return 0;
}

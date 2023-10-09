#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,sinal=1;
    float S=0;
    puts("digite o numero de termos:");
    scanf("%d",&n);
    for(int i=1;i<=n;i+=2){
        float termo=sinal*(1.0/i);
        S+=termo;
        sinal=-sinal;
    }
    printf("Aproximadamente:%f",S*4.0);
    return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    int x,termos;
    float senx1,sinal=-1,senx2;
    double fatorial=1;
    puts("digite o x e o numero de termos:\n");
    scanf("%d %d",&x,&termos);
    if(x>=0 && x<=2){
        for(int i=1;i<=termos;i++){
            fatorial*=i+2;
            senx1+=sinal*(pow(x,i+2)/fatorial);
            sinal=-sinal;
        }
    }else printf("ERRO:X DIGITADO INVALIDO");
    senx2=x-senx1;
    printf("o SENX eh:%f",senx2);
    return 0;
}

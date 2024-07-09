#include <stdio.h>
#include <stdlib.h>

int main()
{
  int operacao;
  float x,y;
    puts("selecione 1 para soma, 2 para subtracao, 3 para multiplicacao e 4 para divisao");
    scanf("%d",&operacao);
    if ((operacao<5)&&(operacao>0)){
        puts("coloque o primeiro numero");
        scanf("%f",&x);
        puts("coloque o segundo numero");
        scanf("%f",&y);
    }
    if(operacao==1){
            printf("a soma dos dois eh: %.2f",x+y);
            }
    else if(operacao==2){
            printf("a subtracao dos dois eh: %.2f",x-y);
    }
    else if(operacao==3){
        printf("a multiplicacao dos dois eh:%.2f", x*y);
    }
    else if(operacao==4){
        printf("a divisao dos dois eh:%.2f",x/y);
    }
    else printf("voce eh burro?");
    return 0;
}

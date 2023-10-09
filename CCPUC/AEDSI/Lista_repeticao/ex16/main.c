#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i=1,n=0;
    float soma=0;
    puts("digite n:");
    scanf("%d",&n);
    while(i<=n){
        soma+=1.0/i;
        i++;}
    printf("a soma eh %.2f:",soma);
    return 0;
}

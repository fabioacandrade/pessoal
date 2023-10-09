#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n;
    double fatorial=1;
    puts("digite n:\n");
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        fatorial*=i;
    }
    printf("o fatorial de %d eh %e",n,fatorial);

    return 0;
}

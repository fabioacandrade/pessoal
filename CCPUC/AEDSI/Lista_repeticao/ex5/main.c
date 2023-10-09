#include <stdio.h>
#include <stdlib.h>

int main()
{
    double p=1;
    for(int i=1;i<=20;i++){
        p*=i;
    }
    printf("o produto eh: %e",p);
    return 0;
}

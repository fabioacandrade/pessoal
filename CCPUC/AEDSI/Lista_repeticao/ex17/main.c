#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num;
    float s=0;
    puts("digite o termo:");
    scanf("%d",&num);
    for(int i=1;i<=num;i++){
            s+= (2.0*i-1/i);

        }
    printf("s=%.2f",s);
    return 0;
}

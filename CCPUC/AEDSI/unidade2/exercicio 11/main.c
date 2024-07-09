#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{
    float s,snovo;
    puts("digite o salario:");
    scanf("%f",&s);
    snovo=s+0.25*s;
    printf("o novo salario eh:%.2f", snovo);

}

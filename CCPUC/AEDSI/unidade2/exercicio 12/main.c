#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{
    float s,snovo,aumento;
    puts("digite o salario e o percentual");
    scanf("%f %f",&s,&aumento);
    snovo=s+aumento/100*s;
    printf("o aumento eh de %.2f /100 e o novo salario %.2f",aumento,snovo);
    return 0;
}

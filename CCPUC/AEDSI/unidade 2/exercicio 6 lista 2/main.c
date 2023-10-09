#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float n1,n2,n3,notafinal;
    puts("digite as 3 notas:");
    scanf("%f %f %f",&n1,&n2,&n3);
    if ((n1>10) || (n1<0) || (n2>10) || (n2<0) || (n3>10) || (n3<0)) printf("VOCE DIGITOU VALORES INVALIDOS");
    notafinal=(n1+n2+n3)/3;
    if ((0<=notafinal) && (notafinal<4))printf("a nota final eh %.2f\nSITUACAO: REPROVADO",notafinal);
    else if ((4<=notafinal)&&(notafinal<7)) printf("a nota final eh %.2f\nSITUACAO:EXAME",notafinal);
    else if ((7<=notafinal)&&(notafinal<=10))printf("a nota final eh %.2f\nSITUACAO:APROVADO",notafinal);
    return 0;
}

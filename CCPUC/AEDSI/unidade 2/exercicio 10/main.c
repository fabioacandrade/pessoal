#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    float a,b,c,x1,x2,delta;
    puts("digite os coeficientes a,b e c:");
    scanf("%f %f %f",&a,&b,&c);
    delta=b*b-4*a*c;
    x1=(-b - sqrt(delta))/2*a;
    x2=(-b + sqrt(delta))/2*a;
    printf ("as raizes sao: %.2f %.2f",x1,x2);

    return 0;
}

#include <stdio.h>
#include <stdlib.h>

int main()
{
    float c,f;
    int conversao;
    puts("digite 1 para converter de F para C e 2 de C para F:");
    scanf("%d",&conversao);
     if(conversao==1){puts("digite a temperatura em fahrenheit:\n");
    scanf("%f",&f);
    printf("%2.2f graus fahrenheit são %2.2f graus celsius", f, (f-32)*5/9);
}
    if(conversao==2){puts("digite a temperatura em celsius:");
    scanf("%f",&c);
    printf("%2.2f graus celsius sao %2.2f graus fahrenheit", c, c*9/5+32);
    }
    return 0;
}

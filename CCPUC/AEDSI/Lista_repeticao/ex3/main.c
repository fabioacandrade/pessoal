#include <stdio.h>
#include <stdlib.h>

int main()
{   float menor,maior,num;
    for(int i=0;i<10;i++){
        printf("digite o %d numero:",i+1);
        scanf("%f",&num);
        if(i==0){
            menor=num;
            maior=num;}
        else {
            if(num<menor) menor=num;
        else if(num>maior) maior=num;
        }
    }
        printf("o menor eh:%.2f e o maior:%.2f",menor, maior);
    }










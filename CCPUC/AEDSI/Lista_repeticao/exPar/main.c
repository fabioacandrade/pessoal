#include <stdio.h>
#include <stdlib.h>

int main()
{
    float n=1;
    for(int i=0;i<5;i++){
        for(int j=0;j<i;j++){
            if(j%2==0)
                n+=1;
            else
                n-=1;
        }
    }
    printf("%f",n);
    return 0;
}

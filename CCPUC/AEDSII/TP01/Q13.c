#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
    char str[1000],char1,char2;
    int tam;
    scanf(" %[^\n]",str);
    do{
        if(!strcmp(str,"FIM"))
            break;
        tam=strlen(str);
        char1=(char)('a'+ (rand()%26));//sorteando os caracteres
        char2=(char)('a'+ (rand()%26));//sorteando os caracteres
        printf("%c %c",char1,char2);
        for(int i=0;i<tam;i++){
            if(str[i]==char1)
                str[i]=char2;
        }
        printf("%s\n",str);
        scanf(" %[^\n]",str);
    }while(1);
    
}
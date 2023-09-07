#include <stdio.h>
#include <string.h>
#include <stdbool.h>
bool isPalindromoRecursivo(char palavra[],int esq,int dir);
int main(){
    while (1){
        char palavra[1000];
        scanf(" %[^\n]",palavra);
        int tam=strlen(palavra);
        if(!strcmp(palavra,"FIM")){//compara a string com a palavra FIM
            break;
        }
        int esq=0;
        int dir=tam-1;
        if(isPalindromoRecursivo(palavra,esq,dir))
            printf("SIM\n");
        else
            printf("NAO\n");
    }
}
bool isPalindromoRecursivo(char palavra[],int esq,int dir){
    bool resp=true;
    if(esq>=dir){//define a parada do codigo
        return true;
    }else{
        if(palavra[esq]!=palavra[dir]){//compara cada caractere com o seu oposto
            resp=false;
        }else{
            resp = resp && isPalindromoRecursivo(palavra,esq+1,dir-1); //chama a função novamente com os valores alterados
        }
        return resp;
    }
}
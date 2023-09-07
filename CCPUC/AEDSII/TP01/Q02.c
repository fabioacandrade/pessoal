#include <stdio.h>
#include <string.h>
void isPalindromo(char palavra[],int tam);
int main(){
    int i=0;
    while (i==0){
        char palavra[1000];
        scanf(" %[^\n]",palavra);
        int tam=strlen(palavra);
        if(!strcmp(palavra,"FIM")){//compara a string com a palavra FIM
            break;
        }
        isPalindromo(palavra,tam);
    }
}
void isPalindromo(char palavra[],int tam){
    int isPali=1;
    for(int i=0;i<tam/2;i++){
            if(palavra[i]!=palavra[tam-i-1]){  //compara a primeira letra com a ultima, a segunda com a penultima e assim por diante
                isPali=0;
                i=tam;
            }
        }
    if(isPali){
        printf("SIM\n");       //IMPRIME NA TELA SE A PALAVRA É OU NAO UM PALINDROMO DEPENDENDO DA VARIAVEL ISPALI
    }else
        printf("NAO\n");
}
#include <stdio.h>
#include <stdlib.h>
//Escreve os numeros no arquivo
void ArquivoWrite(int n){
    FILE *arqWrite = fopen("valores.txt","w");

    for(int i=0;i<n;i++){
        float valor;
        scanf("%f",&valor);
        if(i==n-1)
            fprintf(arqWrite,"%f",valor);
        else
            fprintf(arqWrite,"%f\n",valor);
        
    }

    fclose(arqWrite);
}
//coloca o ponteiro na posição correta para a leitura
void AjusteDoPonteiro(int* Ponteiro,FILE* ArqRead){
    char c;
    fseek(ArqRead,--(*Ponteiro),SEEK_SET);
    while (*Ponteiro>=0 && (c=fgetc(ArqRead))!='\n'){
        fseek(ArqRead,--(*Ponteiro),SEEK_SET);
    }
    fseek(ArqRead,*Ponteiro + 1,SEEK_SET);
}
//lê os numeros escritos no arquivo
void ArquivoRead(int n){
    FILE *arqRead = fopen("valores.txt","r");
    float valorLido;
    fseek(arqRead,0,SEEK_END);
    int Ponteiro = ftell(arqRead);
    //lendo o arquivo de trás para frente
    for(int i=1;i<=n;i++){
        AjusteDoPonteiro(&Ponteiro,arqRead);
        fscanf(arqRead,"%f",&valorLido);
        printf("%g\n",valorLido);
    }

    fclose(arqRead);
}


int main(){

    int n;

    scanf("%d",&n);

    ArquivoWrite(n);

    ArquivoRead(n);

    return 0;
}
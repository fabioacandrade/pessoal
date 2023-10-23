#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[50];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];

}Jogador;

void colocaEspacos(char* str){
    for(int i=0;i<strlen(str);i++){
        if(str[i]==',' && str[i+1]==','){
            for(int j=strlen(str);j>i;j--){
                str[j+1]=str[j];
            }
            str[i+1]='p';
        }
    }
    if(str[strlen(str)-2]==','){
        str[strlen(str)-1]='p';
    }


}

void preencheArray(Jogador *jogador){
    FILE *arq = fopen("players.csv", "r");

    char str[1000];
    
    char* token = strtok(str, ",");
    fgets(str, 1000, arq); //descartando o header

    for (int i = 0; i < 3922; i++) {
        //lendo a proxima linha
        fgets(str, 1000, arq);
        

        //str[strcspn(str, "\n")] = '\0';

        colocaEspacos(str);

        char* token = strtok(str, ",");
        jogador[i].id = atoi(token);

        token = strtok(NULL, ",");
        strcpy(jogador[i].nome, token);

        token = strtok(NULL, ",");
        jogador[i].altura = atoi(token);

        token = strtok(NULL, ",");
        jogador[i].peso = atoi(token);

        //printf("%d\n",i);

        token = strtok(NULL, ",");
        if (strcmp(token, "p") == 0) {
            strcpy(jogador[i].universidade, "nao informado");
        } else {
            strcpy(jogador[i].universidade, token);
        }

        token = strtok(NULL, ",");
        jogador[i].anoNascimento = atoi(token);

        token = strtok(NULL, ",");
        if (strcmp(token, "p") == 0) {
            strcpy(jogador[i].cidadeNascimento, "nao informado");
        } else {
            strcpy(jogador[i].cidadeNascimento, token);
        }

        token = strtok(NULL, ",");
        if (strcmp(token, "p") == 0) {
            strcpy(jogador[i].estadoNascimento, "nao informado");
        } else {
            strcpy(jogador[i].estadoNascimento, token);
        }
        jogador[i].estadoNascimento[strcspn(jogador[i].estadoNascimento, "\n")] = '\0';
    }
    fclose(arq);
}

void swap(Jogador *jogador,int i,int j,int *countTrocas){
    Jogador aux = jogador[i];
    jogador[i]=jogador[j];
    jogador[j]=aux;
    *countTrocas+=3;
}

//algoritmo de ordenacao por bubblesort com a chave sendo anoNascimento e em caso de empate o nome
void bubblesort(Jogador *jogador,int n,int *countComparacoes,int *countTrocas){
    for(int i=0;i<n-1;i++){
        for(int j=0;j<n-i-1;j++){
            if(jogador[j].anoNascimento>jogador[j+1].anoNascimento){
                swap(jogador,j,j+1,countTrocas);
            }
            else if(jogador[j].anoNascimento==jogador[j+1].anoNascimento){
                if(strcmp(jogador[j].nome,jogador[j+1].nome)>0){
                    swap(jogador,j,j+1,countTrocas);
                }
            }
            *countComparacoes+=2;
        }
    }
}



int main(){
    int countComparacoes=0,countTrocas=0;
    float inicioTmp,fimTmp;

    Jogador *jogador = (Jogador*) malloc(3923 * sizeof(Jogador));
    Jogador *copia = (Jogador*) malloc(3923 * sizeof(Jogador));

    preencheArray(jogador);

    //preenchendo o array copia
    char numero[50];
    int n,countCopia=0;
    while(1){
        scanf("%s",numero);
        n=atoi(numero);

        if(!strcmp(numero,"FIM")){
            break;
        }
        else{
            copia[countCopia]=jogador[n];
            countCopia++;
        }
    }
    

    //ordenando o array copia por bubblesort com a chave sendo anoNascimento
    inicioTmp=clock();
    bubblesort(copia,countCopia,&countComparacoes,&countTrocas);
    fimTmp=clock();

    for(int i=0;i<countCopia;i++){
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",copia[i].id,copia[i].nome,copia[i].altura,copia[i].peso,copia[i].anoNascimento,copia[i].universidade,copia[i].cidadeNascimento,copia[i].estadoNascimento);
    }
    
    FILE *arq = fopen("matricula_bubblesort.txt", "w");
    fprintf(arq,"808674\t%d\t%d\t%fs",countComparacoes,countTrocas,(fimTmp-inicioTmp)/1000.0);
    
}
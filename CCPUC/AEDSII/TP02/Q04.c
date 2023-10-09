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
    FILE *arq = fopen("/tmp/players.csv", "r");

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

int main(){
    float inicioTmp,fimTmp;
    inicioTmp=clock();

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
    

    //ordenando o array copia
    for(int i=0;i<countCopia;i++){
        for(int j=0;j<countCopia-1;j++){
            if(strcmp(copia[j].nome,copia[j+1].nome)>0){
                Jogador aux = copia[j];
                copia[j]=copia[j+1];
                copia[j+1]=aux;
            }
        }
    }
    

    //realizando a pesquisa binaria
    char nome[50];
    int countComparacoes=0;
    while(1){
        scanf(" %[^\n]",nome);
        if(!strcmp(nome,"FIM")){
            break;
        }
        else{
            int inicio=0,fim=countCopia-1,meio;
            int achou=0;
            while(inicio<=fim){
                countComparacoes++;
                meio=(inicio+fim)/2;
                countComparacoes++;
                if(strcmp(nome,copia[meio].nome)==0){
                    printf("SIM\n");
                    achou=1;
                    break;
                }
                else if(strcmp(nome,copia[meio].nome)<0){
                    countComparacoes++;
                    fim=meio-1;
                }
                else{
                    countComparacoes++;
                    inicio=meio+1;
                }
            }
            if(!achou){
                printf("NAO\n");
            }
        }
    }
    fimTmp=clock();
    FILE *arq = fopen("matricula_binaria.txt", "w");
    fprintf(arq,"808674\t%lf\t%d",(fimTmp-inicioTmp)/CLOCKS_PER_SEC,countComparacoes);
    
}
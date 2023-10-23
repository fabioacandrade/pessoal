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

void swap(Jogador *jogador,int i,int j,int *countTrocas){
    Jogador aux = jogador[i];
    jogador[i]=jogador[j];
    jogador[j]=aux;
    *countTrocas+=3;
}

int getMaior(Jogador array[], int n ,int *countComparacoes){
    int maior = array[0].id;
    for(int i = 1; i < n; i++){
        countComparacoes++;
        if(array[i].id > maior){
            maior = array[i].id;
        }
    }

    return maior;
}

void insercaoParcial(Jogador *jogador,int n,int *countComparacoes,int *countTrocas){
    for(int i=1;i<n;i++){
        Jogador tmp = jogador[i];
        int j=i-1;
        while(j >= 0 && (jogador[j].anoNascimento > tmp.anoNascimento || (jogador[j].anoNascimento == tmp.anoNascimento && strcmp(jogador[j].nome, tmp.nome) > 0))){
            jogador[j+1]=jogador[j];
            j--;
            *countComparacoes+=2;
            *countTrocas+=1;
        }
        jogador[j+1]=tmp;
        *countTrocas+=1;
    }
}




int main(){
    int countComparacoes=0,countTrocas=0;

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
    

    //chamando o inserçãoParcial
    insercaoParcial(copia,countCopia,&countComparacoes,&countTrocas);
    
    

    for(int i=0;i<10;i++){
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",copia[i].id,copia[i].nome,copia[i].altura,copia[i].peso,copia[i].anoNascimento,copia[i].universidade,copia[i].cidadeNascimento,copia[i].estadoNascimento);
    }
    
    
    
}
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

void colocaEspacos(char* str) {
    int len = strlen(str);
    for (int i = 0; i < len - 1; i++) {
        if (str[i] == ',' && str[i + 1] == ',') {
            for (int j = len; j > i; j--) {
                str[j + 1] = str[j];
            }
            str[i + 1] = 'p';
            len++; // Aumenta o comprimento da string
        }
    }
    if (str[len - 2] == ',') {
        str[len - 1] = 'p';
    }
}

void clone(Jogador *jogador, Jogador *novo, int pos){
    novo->id = jogador[pos].id;
    strcpy(novo->nome, jogador[pos].nome);
    novo->altura = jogador[pos].altura;
    novo->peso = jogador[pos].peso;
    strcpy(novo->universidade, jogador[pos].universidade);
    novo->anoNascimento = jogador[pos].anoNascimento;
    strcpy(novo->cidadeNascimento, jogador[pos].cidadeNascimento);
    strcpy(novo->estadoNascimento, jogador[pos].estadoNascimento);
}

void preencheArray(Jogador *jogador){
    FILE *arq = fopen("/tmp/players.csv", "r");

    char str[1000];
    
    fgets(str, 1000, arq); //descartando o header
    char* token = strtok(str, ",");

    for (int i = 0; i < 3922; i++) {
        //lendo a proxima linha
        fgets(str, 1000, arq);

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

typedef struct{
    Jogador array[7];
    int tam;
    int primeiro;
    int ultimo;
}FilaCircular;

Jogador remover(FilaCircular *fila){
    Jogador resp = fila->array[fila->primeiro];
    fila->primeiro = (fila->primeiro + 1) % fila->tam;
    return resp;
}

void inserirFim(FilaCircular *fila, Jogador jogador){
    if((fila->ultimo + 1) % fila->tam == fila->primeiro){
        fila->primeiro = (fila->primeiro + 1) % fila->tam;
    }
    fila->array[fila->ultimo] = jogador;
    fila->ultimo = (fila->ultimo + 1) % fila->tam;
}


void mostrar(FilaCircular fila){
    int k = fila.primeiro;
    while(k != fila.ultimo){
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", fila.array[k].id, fila.array[k].nome, fila.array[k].altura, fila.array[k].peso, fila.array[k].anoNascimento, fila.array[k].universidade, fila.array[k].cidadeNascimento, fila.array[k].estadoNascimento);
        k = (k + 1) % fila.tam;
    }
}

int mediaAlturas(FilaCircular fila){
    int soma = 0;
    int qtde = 0;
    float media = 0;
    int k = fila.primeiro;
    while(k != fila.ultimo){
        soma += fila.array[k].altura;
        qtde++;
        k = (k + 1) % fila.tam;
    }
    media = (float) soma / qtde;
    media = media + 0.5;
    media = (int) media;
    return media;
}

void setId(FilaCircular *fila){
    int k = fila->primeiro;
    int id = 0;
    while(k != fila->ultimo){
        fila->array[k].id = id;
        id++;
        k = (k + 1) % fila->tam;
    }
}


int main(){

    Jogador *jogador = (Jogador*) malloc(3923 * sizeof(Jogador));
    
    preencheArray(jogador);

    FilaCircular fila;
    fila.tam = 6;
    fila.primeiro = 0;
    fila.ultimo = 0;

    char entrada[100];
    int n = 0;
    scanf("%s", entrada);
    while(strcmp(entrada, "FIM") != 0){
        n = atoi(entrada);
        inserirFim(&fila, jogador[n]);
        printf("%d\n", mediaAlturas(fila));
        scanf("%s", entrada);
    }

    int count = 0;
    scanf("%d", &count);
    //printf("%d\n", count);
    for(int i = 0; i < count; i++){
        scanf("%s", entrada);
        //printf("%s ", entrada);
        if(strcmp(entrada, "I") == 0){
            scanf("%s", entrada);
            n = atoi(entrada);
            inserirFim(&fila, jogador[n]);
            printf("%d\n", mediaAlturas(fila));
        }
        else if(strcmp(entrada, "R") == 0){
            Jogador removido = remover(&fila);
            printf("(R) %s\n", removido.nome);
        }
    }
    setId(&fila);
    mostrar(fila);
    return 0;

    


}
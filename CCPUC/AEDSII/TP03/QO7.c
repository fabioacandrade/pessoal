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

typedef struct Celula {
	Jogador elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

typedef struct {
    Celula* primeiro;
    Celula* ultimo;
    int tam;
} FilaFLexivel;

Celula* novaCelula(Jogador elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

void start(FilaFLexivel *fila){
    fila->primeiro = novaCelula(*((Jogador*) malloc(sizeof(Jogador))));
    fila->ultimo = fila->primeiro;
    fila->tam = 0;
}


Jogador remover(FilaFLexivel *fila) {
    if (fila->primeiro == fila->ultimo) {
        printf("Erro ao remover!");
        exit(1);
    }
    Celula* tmp = fila->primeiro;
    fila->primeiro = fila->primeiro->prox;
    Jogador resp = fila->primeiro->elemento;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    fila->tam--;
    return resp;
}

void inserirFim(FilaFLexivel *fila, Jogador x) {
    if(fila->tam==5){
        remover(fila);
    }
    fila->ultimo->prox = novaCelula(x);
    fila->ultimo = fila->ultimo->prox;
    fila->tam++;
}

int mediaAlturas(FilaFLexivel fila){
    float media = 0;
    int i = 0;
    Celula* j;
    for (j = fila.primeiro->prox; j != NULL; j = j->prox) {
       media += j->elemento.altura;
       i++;
    }
    media = (float)media / i;
    media += 0.5;
    media = (int)media;
    return media;
}

void setId(FilaFLexivel *fila){
    Celula *i;
    int count = 0;
    for(i = fila->primeiro->prox; i != NULL; i = i->prox){
        i->elemento.id = count;
        count++;
    }
}

void mostrar(FilaFLexivel fila){
    Celula *i;
    for(i = fila.primeiro->prox; i != NULL; i = i->prox){
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", i->elemento.id, i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.anoNascimento, i->elemento.universidade, i->elemento.cidadeNascimento, i->elemento.estadoNascimento);
    }
}




int main(){

    Jogador *jogador = (Jogador*) malloc(3923 * sizeof(Jogador));
    
    preencheArray(jogador);

    FilaFLexivel fila;
    start(&fila);
    

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
       // printf("%s ", entrada);
        if(strcmp(entrada, "I") == 0){
            scanf("%s", entrada);
            n = atoi(entrada);
            inserirFim(&fila, jogador[n]);
            printf("%d\n", mediaAlturas(fila));
        }
        else{
            Jogador removido = remover(&fila);
            printf("(R) %s\n", removido.nome);
        }
    }
    setId(&fila);
    mostrar(fila);
    return 0;

    


}
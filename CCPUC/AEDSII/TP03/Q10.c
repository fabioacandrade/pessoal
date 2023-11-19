//pilha com alocação flexivel em c
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
    Jogador elemento;
    struct Celula* prox;
}Celula;

typedef struct{
    Celula* topo;
    int n;
}PilhaFlexivel;

PilhaFlexivel* newPilhaFlexivel(){
    PilhaFlexivel* pilha = (PilhaFlexivel*) malloc(sizeof(PilhaFlexivel));
    pilha->topo = NULL;
    return pilha;
}

void inserir(PilhaFlexivel* pilha, Jogador elemento){
    Celula* tmp = (Celula*) malloc(sizeof(Celula));
    tmp->elemento = elemento;
    tmp->prox = pilha->topo;
    pilha->topo = tmp;
    tmp = NULL;
    pilha->n++;
}

Jogador remover(PilhaFlexivel* pilha){
    if(pilha->topo == NULL){
        printf("Erro ao remover!");
        exit(1);
    }
    Celula* tmp = pilha->topo;
    pilha->topo = pilha->topo->prox;
    Jogador resp = tmp->elemento;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    pilha->n--;
    return resp;
}

//mostre os elementos ao contrario
void mostrarInverso(Celula* i) {
    
    if (i == NULL) {
        return;
    }

    
    mostrarInverso(i->prox);

    
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
        i->elemento.id,
        i->elemento.nome,
        i->elemento.altura,
        i->elemento.peso,
        i->elemento.anoNascimento,
        i->elemento.universidade,
        i->elemento.cidadeNascimento,
        i->elemento.estadoNascimento
    );
}


void mostrarPilhaInverso(PilhaFlexivel* pilha) {
    mostrarInverso(pilha->topo);
}


void setIdPilha(PilhaFlexivel* pilha, int tamanho){
    Celula* i;
    int j = 0;
    for(i = pilha->topo; i != NULL; i = i->prox){
        i->elemento.id = j;
        j++;
    }
}

int main(){
    Jogador *jogador = (Jogador*) malloc(3922 * sizeof(Jogador));
    preencheArray(jogador);
    PilhaFlexivel* pilha = newPilhaFlexivel();
    char entrada[50];
    scanf("%s", entrada);
    while(strcmp(entrada, "FIM") != 0){
        inserir(pilha, jogador[atoi(entrada)]);
        scanf("%s", entrada);
    }
    int n = 0;
    scanf("%d", &n);
    for(int i = 0; i < n; i++){
        scanf("%s", entrada);
        if(strcmp(entrada, "I") == 0){
            scanf("%s", entrada);
            inserir(pilha, jogador[atoi(entrada)]);
        }else if(strcmp(entrada, "R") == 0){
            printf("(R) %s\n", remover(pilha).nome);
        }
    }
    setIdPilha(pilha, pilha->n);
    mostrarPilhaInverso(pilha);
    return 0;
}


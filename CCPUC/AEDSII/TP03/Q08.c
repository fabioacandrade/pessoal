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
	struct Celula* prox; 
    struct Celula* ant;
} Celula;

typedef struct {
    Celula* primeiro;
    Celula* ultimo;
} ListaDupla;

float inicioTempo = 0, fimTempo = 0;
int comp = 0, mov = 0;

void start(ListaDupla *lista){
    lista->primeiro = (Celula*) malloc(sizeof(Celula));
    lista->ultimo = lista->primeiro;
    lista->primeiro->prox = NULL;
    lista->primeiro->ant = NULL;
}

void inserir(ListaDupla *lista, Jogador x){
    lista->ultimo->prox = (Celula*) malloc(sizeof(Celula));
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
    lista->ultimo->elemento = x;
    lista->ultimo->prox = NULL;
}

void mostrar(ListaDupla lista){
    Celula* i;
    for(i = lista.primeiro->prox; i != NULL; i = i->prox){
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", i->elemento.id, i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.anoNascimento, i->elemento.universidade, i->elemento.cidadeNascimento, i->elemento.estadoNascimento);
    }
}

void swap(Jogador *a, Jogador *b){
    Jogador temp = *a;
    *a = *b;
    *b = temp;
    mov += 3;
}

void quicksortRecursivo(Celula *esq, Celula *dir);

void quicksort(ListaDupla *lista){
    quicksortRecursivo(lista->primeiro->prox, lista->ultimo);
}

void quicksortRecursivo(Celula *esq, Celula *dir){
    Celula* i = esq;
    Celula* j = dir;
    Celula* pivot = dir;


    while(i->ant != j && i->ant != j->prox){
        comp++;
        while(strcmp(i->elemento.estadoNascimento, pivot->elemento.estadoNascimento) < 0 ||
              (strcmp(i->elemento.estadoNascimento, pivot->elemento.estadoNascimento) == 0 &&
               strcmp(i->elemento.nome, pivot->elemento.nome) < 0)){
            i = i->prox;
            comp++;
        }

        comp++;
        while(strcmp(j->elemento.estadoNascimento, pivot->elemento.estadoNascimento) > 0 ||
              (strcmp(j->elemento.estadoNascimento, pivot->elemento.estadoNascimento) == 0 &&
               strcmp(j->elemento.nome, pivot->elemento.nome) > 0)){
            j = j->ant;
            comp++;
        }

        if(i->ant != j && i->ant != j->prox){
            swap(&i->elemento, &j->elemento);
            i = i->prox;
            j = j->ant;
        }
    }

    if(j != esq && esq->ant != j){
        quicksortRecursivo(esq, j);
    }
    if(i != dir && dir->prox != i){
        quicksortRecursivo(i, dir);
    }
}








int main(){

    Jogador *jogador = (Jogador*) malloc(3923 * sizeof(Jogador));
    
    preencheArray(jogador);

    ListaDupla lista;
    start(&lista);
    

    char entrada[100];
    int n = 0;
    scanf("%s", entrada);
    while(strcmp(entrada, "FIM") != 0){
        n = atoi(entrada);
        inserir(&lista, jogador[n]);
        scanf("%s", entrada);
    }

    //mostrar(lista);

    //algoritmo de ordenação quicksort
    inicioTempo = clock();
    quicksort(&lista);
    fimTempo = clock();

    
    mostrar(lista);

    FILE *arq = fopen("808674_quicksort2.txt", "w");
    fprintf(arq, "808674\t%d\t%d\t%lf", comp, mov, (fimTempo - inicioTempo)/1000.0);
    return 0;

    


}
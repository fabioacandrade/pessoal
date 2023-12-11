//tabela hash indireta com lista simples


//arvore AVL
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdbool.h>

typedef struct Jogador{
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

static int comparacoes = 0;

typedef struct Celula{
    char nome[50];
    struct Celula *prox;
}Celula;

typedef struct Lista{
    Celula *primeiro;
    Celula *ultimo;
}Lista;

typedef struct Hash{
    Lista *tabela[25];
    int tam;
}Hash;

//cria uma nova celula
Celula *novaCelula(char* nome){
    Celula *nova = (Celula*) malloc(sizeof(Celula));
    strcpy(nova->nome, nome);
    nova->prox = NULL;
    return nova;
}
//cria uma nova lista
Lista *novaLista(){
    Lista *nova = (Lista*) malloc(sizeof(Lista));
    nova->primeiro = novaCelula("");
    nova->ultimo = nova->primeiro;
    return nova;
}

//insere uma nova celula no final da lista
void inserirLista(Lista *lista, char* nome){
    lista->ultimo->prox = novaCelula(nome);
    lista->ultimo = lista->ultimo->prox;
}

//busca uma celula na lista
bool buscarLista(Lista *lista, char* nome){
    Celula *i;
    for(i = lista->primeiro->prox; i != NULL; i = i->prox){
        comparacoes++;
        if(strcmp(i->nome, nome) == 0){
            return true;
        }
    }
    return false;
}

//cria uma nova tabela hash
Hash *novaHash(){
    Hash *nova = (Hash*) malloc(sizeof(Hash));
    nova->tam = 25;
    for(int i = 0; i < 25; i++){
        nova->tabela[i] = novaLista();
    }
    return nova;
}

//função hash
int hash(int altura){
    return altura % 25;
}

//insere um novo jogador na tabela hash
void inserirHash(Hash *tabelahash, char* nome, int altura){
    int pos = hash(altura);
    inserirLista(tabelahash->tabela[pos], nome);
}

//busca um jogador na tabela hash
bool buscarHash(char* nome, Hash *tabelahash){
    bool resp = false;
    for(int i = 0; i < 25; i++){
        comparacoes++;
        if(buscarLista(tabelahash->tabela[i], nome)){
            resp = true;
            break;
        }
    }
}


int main(){
    Jogador *jogador = (Jogador*) malloc(3922 * sizeof(Jogador));
    preencheArray(jogador);
    
    Hash *tabelahash = novaHash();

    clock_t tempoInicial = clock();

    char entrada[300];

    scanf("%s", entrada);
    entrada[(int)strcspn(entrada, "\n\r")] = '\0';

    while(strcmp(entrada, "FIM") != 0){
        //printf("%s\n", jogador[atoi(entrada)].nome);
        inserirHash(tabelahash, jogador[atoi(entrada)].nome, jogador[atoi(entrada)].altura);
        getchar();
        scanf("%s", entrada);
        entrada[(int)strcspn(entrada, "\n\r")] = '\0';
    }

    getchar();

    scanf(" %[^\n]", entrada);
    entrada[(int)strcspn(entrada, "\n\r")] = '\0';

    while (strcmp(entrada, "FIM") != 0)
    {
        printf("%s", entrada);
        if (buscarHash(entrada, tabelahash))
        {
            printf(" SIM\n");
        }
        else
        {
            printf(" NAO\n");
        }
        getchar();
        scanf(" %[^\n]", entrada);
        entrada[(int)strcspn(entrada, "\n\r")] = '\0';
    }
    
    clock_t tempoFinal = clock();
   

    FILE *arq = fopen("matrícula_hashIndireta.txt", "w");
    fprintf(arq, "808674\t%d\t%d", comparacoes, (tempoFinal - tempoInicial));
    return 0;
}


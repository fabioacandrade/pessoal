
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

typedef struct No{
    Jogador jogador;
    struct No* esq;
    struct No* dir;
    int altura;
}No;

typedef struct ArvoreAVL{
    No *raiz;
}ArvoreAVL;

//função para criar um novo no
No* novoNo(Jogador jogador){
    No* no = (No*) malloc(sizeof(No));
    no->jogador = jogador;
    no->esq = NULL;
    no->dir = NULL;
    no->altura = 1;
    return no;
}

//função para pegar a altura
int getAltura(No *no){
    if(no == NULL){
        return 0;
    }
    return no->altura;
}

//função para setar a altura
void setAltura(No *no){
    no->altura = 1 + (getAltura(no->esq) > getAltura(no->dir) ? getAltura(no->esq) : getAltura(no->dir));
}

//função para pesquisar o jogador
bool pesquisar(No *no, char *nome){
    bool resp = false;
    if(no != NULL){
        comparacoes++;
        if(strcmp(no->jogador.nome, nome) == 0){
            resp = true;
            comparacoes++;
        }else if(strcmp(no->jogador.nome, nome) > 0){
            printf("esq ");
            resp = pesquisar(no->esq, nome);
        }else{
            printf("dir ");
            resp = pesquisar(no->dir, nome);
        }
    }
    return resp;
}

//função para rotacionar a arvore para a direita
No* rotacionarDir(No *no){
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    setAltura(no);
    setAltura(noEsq);

    return noEsq;
}

//função para rotacionar a arvore para a esquerda
No* rotacionarEsq(No *no){
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setAltura(no);
    setAltura(noDir);

    return noDir;
}

//função para pegar o fator de balanceamento
int getBalanceamento(No *no){
    if(no == NULL){
        return 0;
    }
    return getAltura(no->esq) - getAltura(no->dir);
}

//função para balancear a arvore
No* balancear(No *no){
    if(no != NULL){
        int fator = getBalanceamento(no);
        if(abs(fator) <= 1){
            setAltura(no);
        }else if(fator == 2){
            int fatorFilho = getBalanceamento(no->esq);
            if(fatorFilho == -1){
                no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);
        }else if(fator == -2){
            int fatorFilho = getBalanceamento(no->dir);
            if(fatorFilho == 1){
                no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);
        }else{
            printf("Erro ao balancear!\n");
        }   
    }
    return no;
}

//inserindo o jogador e retornando o endereço do no
No* inserir(No *no, Jogador jogador){
 
    if (no == NULL)
    {
        no = novoNo(jogador);
    }
    else if (strcmp(jogador.nome, no->jogador.nome) < 0)
    {
        
        no->esq = inserir(no->esq, jogador);
    }
    else if (strcmp(jogador.nome, no->jogador.nome) > 0)
    {
        
        no->dir = inserir(no->dir, jogador);
    }
    else
    {
        printf("Erro ao inserir!\n");
    }
    return balancear(no);
}

int main(){
    Jogador *jogador = (Jogador*) malloc(3922 * sizeof(Jogador));
    preencheArray(jogador);
    
    ArvoreAVL *arvore = (ArvoreAVL*) malloc(sizeof(ArvoreAVL));

    arvore->raiz = NULL;

    clock_t tempoInicial = clock();

    char entrada[300];

    scanf("%s", entrada);

    while(strcmp(entrada, "FIM") != 0){
        arvore->raiz = inserir(arvore->raiz, jogador[atoi(entrada)]);
        scanf("%s", entrada);
    }

    getchar();
    
    scanf("%[^\n]", entrada);

    printf("%s",entrada);

    while(strcmp(entrada, "FIM") != 0){
        printf("%s raiz ", entrada);
        if(pesquisar(arvore->raiz, entrada)){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }
        getchar();
        scanf("%[^\n]", entrada);
    }
    clock_t tempoFinal = clock();

    FILE *arq = fopen("matrícula_avl.txt", "w");
    fprintf(arq, "808674\t%d\t%d", comparacoes, (tempoFinal - tempoInicial));
    return 0;
}


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

int comp = 0;

typedef struct
{ // Struct para guardar os dados dos jogadores
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];

} Jogador;

void imprimir(Jogador *jogador)
{ // Funcao para imprimir os dados dos jogadores
    printf("[%i] ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

void replaceVirgula(char *str)
{
    int tamanho = strlen(str);
    char tmp[3 * tamanho];
    int j = 0;

    for (int i = 0; i < tamanho; i++)
    {
        if (str[i] == ',' && str[i + 1] == ',')
        {
            tmp[j++] = ',';
            tmp[j++] = 'n';
            tmp[j++] = 'a';
            tmp[j++] = 'o';
            tmp[j++] = ' ';
            tmp[j++] = 'i';
            tmp[j++] = 'n';
            tmp[j++] = 'f';
            tmp[j++] = 'o';
            tmp[j++] = 'r';
            tmp[j++] = 'm';
            tmp[j++] = 'a';
            tmp[j++] = 'd';
            tmp[j++] = 'o';
            tmp[j++] = ',';

            i++;
        }
        else
        {
            tmp[j++] = str[i];
        }
    }

    if (tmp[j - 2] == ',')
    {
        tmp[j - 1] = 'n';
        tmp[j++] = 'a';
        tmp[j++] = 'o';
        tmp[j++] = ' ';
        tmp[j++] = 'i';
        tmp[j++] = 'n';
        tmp[j++] = 'f';
        tmp[j++] = 'o';
        tmp[j++] = 'r';
        tmp[j++] = 'm';
        tmp[j++] = 'a';
        tmp[j++] = 'd';
        tmp[j++] = 'o';
    }

    tmp[j] = '\0';
    strcpy(str, tmp);
}

void ler(char str[300], Jogador *jogador)
{                        // Funcao para ler os dados dos jogadores
    replaceVirgula(str); /* Como a funcao strtok ignora quando temos vazio, por exemplo ",," ,temos que substituir os valores antes de fazer a separaçao*/

    str[strcspn(str, "\n")] = '\0'; // Remove o '\n' no final da string
    char *tmp;
    int i = 0;
    tmp = strtok(str, ","); // Separa a string em virgulas

    while (tmp != NULL)
    {
        if (i % 8 == 0)
        {
            jogador->id = atoi(tmp);
        }
        else if (i % 8 == 1)
        {
            strcpy(jogador->nome, tmp);
        }
        else if (i % 8 == 2)
        {
            jogador->altura = atoi(tmp);
        }
        else if (i % 8 == 3)
        {
            jogador->peso = atoi(tmp);
        }
        else if (i % 8 == 4)
        {
            strcpy(jogador->universidade, tmp);
        }
        else if (i % 8 == 5)
        {
            jogador->anoNascimento = atoi(tmp);
        }
        else if (i % 8 == 6)
        {
            strcpy(jogador->cidadeNascimento, tmp);
        }
        else if (i % 8 == 7)
        {
            strcpy(jogador->estadoNascimento, tmp);
        }
        i++;

        tmp = strtok(NULL, ",");
    }
}

void clone(Jogador *jogador, Jogador *novo)
{ // Funcao para clonar um jogador
    novo->id = jogador->id;
    strcpy(novo->nome, jogador->nome);
    novo->altura = jogador->altura;
    novo->peso = jogador->peso;
    strcpy(novo->universidade, jogador->universidade);
    novo->anoNascimento = jogador->anoNascimento;
    strcpy(novo->cidadeNascimento, jogador->cidadeNascimento);
    strcpy(novo->estadoNascimento, jogador->estadoNascimento);
}

typedef struct No
{
    struct No *esq;
    struct No *dir;
    Jogador jogador;
    int nivel;
} No;


/**
 * Cria um novo no
 * 
 * @param jogador Jogador a ser inserido no no
 * @return No criado
 * 
*/
No *novoNo(Jogador jogador)
{
    No *tmp = (No *)malloc(sizeof(No));
    tmp->esq = NULL;
    tmp->dir = NULL;
    tmp->jogador = jogador;
    tmp->nivel = 1;
    return tmp;
}

/**
 * Retorna o nivel do no
 * 
 * @param no No a ser analisado
 * @return Nivel do no
 * 
*/
int getNivel(No *no)
{
    return (no == NULL) ? 0 : no->nivel;
}

/**
 * Atualiza o nivel do no
 * 
 * @param no No a ser atualizado
 * 
*/
void setNivel(No *no)
{
    no->nivel = 1 + (getNivel(no->esq) > getNivel(no->dir) ? getNivel(no->esq) : getNivel(no->dir));
}

typedef struct
{
    No *raiz;
} AVL;


/**
 * Pesquisa um jogador na arvore
 * 
 * @param nome Nome do jogador a ser pesquisado
 * @param no No a ser analisado
 * @return True se o jogador foi encontrado, false caso contrario
 * 
*/
bool pesquisar(char *nome, No *no)
{
    bool resp;
    if (no == NULL)
    {
        resp = false;
    }
    else if (strcmp(nome, no->jogador.nome) == 0)
    {
        comp++;
        resp = true;
    }
    else if (strcmp(nome, no->jogador.nome) < 0)
    {
        comp+= 2;
        printf(" esq");
        resp = pesquisar(nome, no->esq);
    }
    else
    {
        comp+= 2;
        printf(" dir");
        resp = pesquisar(nome, no->dir);
    }
    return resp;
}

/**
 * Rotaciona a arvore para a esquerda
 * 
 * @param no No a ser rotacionado
 * @return No rotacionado
 * 
*/
No *rotacionarEsq(No *no)
{
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setNivel(no);
    setNivel(noDir);

    return noDir;
}


/**
 * Rotaciona a arvore para a direita
 * 
 * @param no No a ser rotacionado
 * @return No rotacionado
 * 
*/
No *rotacionarDir(No *no)
{
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    setNivel(no);
    setNivel(noEsq);

    return noEsq;
}


/**
 * Balanceia a arvore
 * 
 * @param no No a ser balanceado
 * @return No balanceado
 * 
*/
No *balancear(No *no)
{
    if (no != NULL)
    {
        int fator = getNivel(no->dir) - getNivel(no->esq);
        // Se balanceada
        if (abs(fator) <= 1)
        {
            setNivel(no);
        }
        // Se desbalanceada para a direita
        else if (fator == 2)
        {
            int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);
            if (fatorFilhoDir == -1)
            {
                no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);
        }
        // Se desbalanceada para a esquerda
        else if (fator == -2)
        {
            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);
            if (fatorFilhoEsq == 1)
            {
                no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);
        }
        else
        {
            printf("Erro fator de balanceamento %d de no: %s id: %i\n", fator, no->jogador.nome, no->jogador.id);
        }
    }
    return no;
}


/**
 * Caminha pela arvore em ordem central
 * 
 * @param no No a ser analisado
 * 
*/
void caminharCentral(No *no)
{
    if (no != NULL)
    {
        printf("%s\n", no->jogador.nome);
        // printf("vou para esq\n");
        caminharCentral(no->esq);
        // printf("vou para dir\n");
        caminharCentral(no->dir);
    }
}


/**
 * Insere um jogador na arvore
 * 
 * @param jogador Jogador a ser inserido
 * @param no No a ser analisado
 * @return No inserido
 * 
*/
No *inserir(Jogador jogador, No *no)
{
    if (no == NULL)
    {
        no = novoNo(jogador);
    }
    else if (strcmp(jogador.nome, no->jogador.nome) < 0)
    {
        no->esq = inserir(jogador, no->esq);
    }
    else if (strcmp(jogador.nome, no->jogador.nome) > 0)
    {
        no->dir = inserir(jogador, no->dir);
    }
    else
    {
        printf("Erro ao inserir!\n");
    }
    return balancear(no);
}

int main()
{
    Jogador players[3923];
    char n[5];

    FILE *arq;
    arq = fopen("players.csv", "r");

    if (arq == NULL)
    {
        printf("Erro ao abrir o arquivo\n");
        return 0;
    }

    char str[300];
    fgets(str, sizeof(str), arq); // ler a primeira linha do arquivo
    int i = 0;
    while (fgets(str, sizeof(str), arq))
    { // ler as outras linhas do arquivo
        ler(str, &players[i]);
        i++;
    }
    fclose(arq);

    Jogador tmp;
    AVL arvore;
    arvore.raiz = NULL;
    scanf("%s", n);

    while (strcmp(n, "FIM") != 0)
    { // Clonando os jogadores para o hash
        clone(&players[atoi(n)], &tmp);

        arvore.raiz = inserir(tmp, arvore.raiz);

        scanf("%s", n);
    }

    char nome[50];
    getchar();
    scanf(" %[^\n]", nome);

    clock_t inicio = clock();
    while (strcmp(nome, "FIM") != 0)
    { // Verificando se existe
        printf("%s raiz", nome);
        if (pesquisar(nome, arvore.raiz))
        {
            printf(" SIM\n");
        }
        else
        {
            printf(" NAO\n");
        }

        getchar();
        scanf(" %[^\n]", nome);
    }
    clock_t fim = clock();

    double total = (fim - inicio) / (double)CLOCKS_PER_SEC / 1000.0;
    arq = fopen("807205_avl.txt", "w");
    fprintf(arq, "807205\t%f\t%i",total, comp);

    return 0;
}
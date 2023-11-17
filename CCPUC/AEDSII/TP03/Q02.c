//refazendo a questao QO1 em c
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
    Jogador *array;
    int n;
}Lista;

void inserirFim(Lista *lista, Jogador jogador){
    
    if(lista->n==0){
        lista->array[0]=jogador;
        lista->n++;
    }
    else{
        lista->array[lista->n]=jogador;
        lista->n++;
    }
}

void inserirInicio(Lista *lista, Jogador jogador){
    if(lista->n==0){
        lista->array[0]=jogador;
        lista->n++;
    }
    else{
        for(int i=lista->n;i>0;i--){
            lista->array[i]=lista->array[i-1];
        }
        lista->array[0]=jogador;
        lista->n++;
    }
}

void inserir(Lista *lista, Jogador jogador, int pos){
    if(lista->n==0){
        lista->array[0]=jogador;
        lista->n++;
    }
    else{
        for(int i=lista->n;i>pos;i--){
            lista->array[i]=lista->array[i-1];
        }
        lista->array[pos]=jogador;
        lista->n++;
    }
}

Jogador removerInicio(Lista *lista){
    Jogador removido = lista->array[0];
    for(int i=0;i<lista->n;i++){
        lista->array[i]=lista->array[i+1];
    }
    lista->n--;
    return removido;
}

Jogador removerFim(Lista *lista){
    Jogador removido = lista->array[lista->n-1];
    lista->n--;
    return removido;
}

Jogador remover(Lista *lista, int pos){
    Jogador removido = lista->array[pos];
    for(int i=pos;i<lista->n;i++){
        lista->array[i]=lista->array[i+1];
    }
    lista->n--;
    return removido;
}

void mostrar(Lista *lista){
    for(int i=0;i<lista->n;i++){
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",lista->array[i].id,lista->array[i].nome,lista->array[i].altura,lista->array[i].peso,lista->array[i].anoNascimento,lista->array[i].universidade,lista->array[i].cidadeNascimento,lista->array[i].estadoNascimento);
    }
}

void setId(Lista *lista){
    for(int i=0;i<lista->n;i++){
        lista->array[i].id=i;
    }
}

int main(){

    Jogador *jogador = (Jogador*) malloc(3923 * sizeof(Jogador));

    preencheArray(jogador);

    Lista *lista = (Lista*) malloc(sizeof(Lista));
    lista->array = (Jogador*) malloc(3923 * sizeof(Jogador));
    lista->n=0;

    char numero[1000];
    int n;

    fgets(numero, 1000, stdin);
    numero[strcspn(numero, "\n")] = '\0';

    while (strcmp(numero, "FIM") != 0) {
        n = atoi(numero);
        Jogador aux;
        clone(jogador,&aux,n);
        inserirFim(lista,aux);
        fgets(numero, 1000, stdin);
        numero[strcspn(numero, "\n")] = '\0';
    }

    //mostrar(lista);


    char entrada[1000];
    fgets(entrada, 1000, stdin);
    entrada[strcspn(entrada, "\n")] = '\0';
    int count = atoi(entrada);

    //printf("%d\n",count);

    for(int i=0;i<count;i++){
        scanf("%s",entrada);
        //printf("%s\n ",entrada);
        if(strcmp(entrada,"II")==0){
            Jogador aux;
            scanf("%s",entrada);
            n=atoi(entrada);
            clone(jogador,&aux,n);
            inserirInicio(lista,aux);
            //printf("inserido inicio %s\n",aux.nome);
            //mostrar(lista);
        }
        else if(strcmp(entrada,"IF")==0){
            Jogador aux;
            scanf("%s",entrada);
            n=atoi(entrada);
            clone(jogador,&aux,n);
            inserirFim(lista,aux);
            //printf("inserido fim %s\n",aux.nome);
            //mostrar(lista);
        }
        else if(strcmp(entrada,"I*")==0){
            Jogador aux;
            scanf("%s",entrada);
            n=atoi(entrada);
            clone(jogador,&aux,n);
            scanf("%s",entrada);
            n=atoi(entrada);
            inserir(lista,aux,n);
            //printf("inserido %s\n",aux.nome);
            //mostrar(lista);
        }
        else if(strcmp(entrada,"RI")==0){
            Jogador removido = removerInicio(lista);
            printf("(R) %s\n",removido.nome);
            
        }
        else if(strcmp(entrada,"RF")==0){
            //mostrar(lista);
            Jogador removido = removerFim(lista);
            printf("(R) %s\n",removido.nome);
        }
        else if(strcmp(entrada,"R*")==0){
            scanf("%s",entrada);
            n=atoi(entrada);
            Jogador removido = remover(lista,n);
            printf("(R) %s\n",removido.nome);
        }
    }

    //consertando os ids
    setId(lista);
    mostrar(lista);

    free(jogador);
    free(lista->array);
    free(lista);

    
}
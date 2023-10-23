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
    FILE *arq = fopen("players.csv", "r");

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

void clone(Jogador *jogador,Jogador *copia){
    copia->id = jogador->id;
    strcpy(copia->nome,jogador->nome);
    copia->altura = jogador->altura;
    copia->peso = jogador->peso;
    strcpy(copia->universidade,jogador->universidade);
    copia->anoNascimento = jogador->anoNascimento;
    strcpy(copia->cidadeNascimento,jogador->cidadeNascimento);   
    strcpy(copia->estadoNascimento,jogador->estadoNascimento);
}

void constroi(Jogador *jogador,int tamHeap,int i,int *countComparacoes,int *countTrocas){
    for(int i = tamHeap; i > 1 && jogador[i].altura > jogador[i/2].altura || jogador[i].altura == jogador[i/2].altura && strcmp(jogador[i].nome, jogador[i/2].nome) > 0; i /=2){
        swap(jogador,i,i/2,countTrocas);
    }
}

int getMaiorFilho(Jogador *jogador,int i,int tamHeap,int *countComparacoes){
    int filho;
    if (2*i == tamHeap || jogador[2*i].altura > jogador[2*i+1].altura || jogador[2*i].altura == jogador[2*i+1].altura && strcmp(jogador[2*i].nome, jogador[2*i+1].nome) > 0){
        filho = 2*i;
    } else {
        filho = 2*i + 1;
    }
    return filho;
}

void reconstroi(Jogador *jogador,int tamHeap,int *countComparacoes,int *countTrocas){
    int i = 1;
    while(i <= (tamHeap/2)){
        int filho = getMaiorFilho(jogador,i,tamHeap,countComparacoes);
        if(jogador[i].altura < jogador[filho].altura || jogador[i].altura == jogador[filho].altura && strcmp(jogador[i].nome, jogador[filho].nome) < 0){
            swap(jogador,i,filho,countTrocas);
            i = filho;
        } else {
            i = tamHeap;
        }
    }
}


void heapsortParcial(Jogador *jogador,int n,int *countComparacoes,int *countTrocas){
    //Alterar o vetor ignorando a posicao zero
    Jogador *tmp = (Jogador*) malloc((n+1) * sizeof(Jogador));
    for(int i = 0; i < n; i++){
        tmp[i+1] = jogador[i];
    }

    //Contrucao do heap com os 10 primeiros elementos
    int k = 10;
    for(int tamHeap = 2; tamHeap <= k; tamHeap++){
        constroi(tmp,tamHeap,tamHeap,countComparacoes,countTrocas);
    }

    //comparando os 10 primeiros elementos com o restante do array
    for(int i = k+1; i <= n; i++){
        if(tmp[i].altura < tmp[1].altura || tmp[i].altura == tmp[1].altura && strcmp(tmp[i].nome, tmp[1].nome) < 0){
            swap(tmp,i,1,countTrocas);
            reconstroi(tmp,10,countComparacoes,countTrocas);
        }
    }
    
    //Ordenacao propriamente dita
    int tamHeap = k;
    while(tamHeap > 1){
        swap(tmp,1,tamHeap,countTrocas);
        tamHeap--;
        reconstroi(tmp,tamHeap,countComparacoes,countTrocas);
    }

    //Alterar o vetor para voltar a posicao zero
    for(int i = 0; i < n; i++){
        jogador[i] = tmp[i+1];
    }
    free(tmp);
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
    

    //chamando o heapsortParcial utilizando a altura como chave e o k=10
    heapsortParcial(copia,countCopia,&countComparacoes,&countTrocas);
    
    

    for(int i=0;i<10;i++){
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",copia[i].id,copia[i].nome,copia[i].altura,copia[i].peso,copia[i].anoNascimento,copia[i].universidade,copia[i].cidadeNascimento,copia[i].estadoNascimento);
    }
    
    
    
}
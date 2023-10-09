#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[50];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

int main() {
    FILE *arq = fopen("players.csv", "r");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    Jogador *jogador = (Jogador *)malloc(3922 * sizeof(Jogador));
    if (jogador == NULL) {
        perror("Erro ao alocar memória");
        fclose(arq);
        return 1;
    }

    char str[100];
    fgets(str, sizeof(str), arq); // Lê a primeira linha de cabeçalho, se houver

    for (int i = 0; i < 3922; i++) {
        if (fgets(str, sizeof(str), arq) == NULL) {
            break; // Se não houver mais dados, saia do loop
        }

        char *token = strtok(str, ",");
        jogador[i].id = atoi(token);
        token = strtok(NULL, ",");
        strcpy(jogador[i].nome, token);
        token = strtok(NULL, ",");
        jogador[i].altura = atoi(token);
        token = strtok(NULL, ",");
        jogador[i].peso = atoi(token);
        token = strtok(NULL, ",");
        strcpy(jogador[i].universidade, token);
        token = strtok(NULL, ",");
        jogador[i].anoNascimento = atoi(token);
        token = strtok(NULL, ",");
        strcpy(jogador[i].cidadeNascimento, token);
        token = strtok(NULL, ",");
        strcpy(jogador[i].estadoNascimento, token);
    }

    // Imprime os nomes dos jogadores
    for (int i = 0; i < 3922; i++) {
        printf("Nome do jogador %d: %s\n", jogador[i].id, jogador[i].nome);
    }

    // Libera a memória alocada e fecha o arquivo
    free(jogador);
    fclose(arq);

    return 0;
}

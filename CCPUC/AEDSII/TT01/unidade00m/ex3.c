/*• Faça um programa que leia n números inteiros e os mostre a soma do
primeiro e último, segundo e penúltimo e assim sucessivamente.
Novamente, sem usar arrays*/
#include <stdio.h>
int main(){
    int n, i, num, soma;
    FILE *arq;
    arq = fopen("numeros.txt", "w");
    printf("Digite a quantidade de numeros: ");
    scanf("%d", &n);
    for(i=0; i<n; i++){
        printf("Digite um numero: ");
        scanf("%d", &num);
        fprintf(arq, "%d\n", num);
    }
    fclose(arq);
    arq = fopen("numeros.txt", "r");
    for(i=0; i<n; i++){
        fscanf(arq, "%d", &num);
        printf("%d\n", num);
    }
    fclose(arq);
    return 0;
}
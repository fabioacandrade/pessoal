#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void EspelharStr(char* str, char* strEspelho) {
    int tam = strlen(str);
    for (int i = 1; i <= tam; i++) {
        strEspelho[i-1] =str[tam - i];
    }
}

void preencherStr(int n1, int n2, char* str) {
    int j = 0;
    for (int i = n1; i <= n2; i++) {
        str[j] = '0' + i; // Convert integer to character representing its digit
        j++;
    }
}

int main() {
    int n1, n2;
    char str[50], strEspelho[50];
    do {
        scanf("%d %d", &n1, &n2);
        if (n1 > n2) {  
            break; // Exit the loop if n1 is greater than n2
        }
        preencherStr(n1, n2, str);
        EspelharStr(str, strEspelho);
        printf("%s%s\n", str, strEspelho);
    } while (1); // You can use 1 instead of true for an infinite loop
    return 0;
}

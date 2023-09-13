#include <stdio.h>
#include <stdlib.h>
 
int main(){
	int tam;
	while(scanf("%d",&tam)!=EOF){
		int livros[tam];
		for(int i=0;i<tam;i++){
			scanf("%d",&livros[i]);
		}
		for(int i=0;i<tam;i++){
			int menor=livros[i];
			for(int j=i+1;j<tam-i;j++){
				if(livros[j]<livros[i])
					menor=livros[j];
			}
			printf("%d",menor);
		}	
	}
}

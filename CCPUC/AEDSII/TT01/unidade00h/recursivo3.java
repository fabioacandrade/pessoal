/*• Faça um método recursivo que receba um array de caracteres e retorne um 
número inteiro indicando a quantidade de vogais do mesmo*/
import java.util.Scanner;
public class recursivo3 {
    public static int vogais(char[] vet, int i){
        if(i==vet.length){
            return 0;
        }
        else{
            if(vet[i]=='a' || vet[i]=='e' || vet[i]=='i' || vet[i]=='o' || vet[i]=='u'){
                return 1+vogais(vet,i+1);
            }
            else{
                return vogais(vet,i+1);
            }
        }
    }
}

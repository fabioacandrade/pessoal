/* Faça um método recursivo que receba um array de caracteres e retorne um 
valor booleano indicando se esse é um palíndromo */
import java.util.Scanner;

public class recursivo2 {
    public static boolean palindromo(char[] vet, int i, int j){
        if(i>=j){
            return true;
        }
        else{
            if(vet[i]!=vet[j]){
                return false;
            }
            else{
                return palindromo(vet,i+1,j-1);
            }
        }
    }
}

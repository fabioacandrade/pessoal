/*• Faça um método recursivo que receba dois números inteiros e retorne a 
multiplicação do primeiro pelo segundo fazendo somas */
import java.util.Scanner;

public class recursivo1 {
    public static int multiplica(int a, int b){
        if(b==0){
            return 0;
        }
        else{
            return a+multiplica(a,b-1);
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a,b;
        System.out.println("Digite um número");
        a = in.nextInt();
        System.out.println("Digite outro número");
        b = in.nextInt();
        System.out.println("O resultado da multiplicação é "+multiplica(a,b));
    }
}

/*• Faça um programa que leia uma String e um caractere e mostre na tela quantas vezes esse caractere aparece na String */
import java.util.Scanner;


public class string {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str;
        char c;
        int cont=0;
        System.out.println("Digite uma string");
        str = in.nextLine();
        System.out.println("Digite um caractere");
        c = in.next().charAt(0);
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==c){
                cont++;
            }
        }
        System.out.println("O caractere "+c+" aparece "+cont+" vezes na string "+str);
    }
}

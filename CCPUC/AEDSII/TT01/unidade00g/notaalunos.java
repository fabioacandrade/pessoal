/* Faça um programa para ler a nota de cinco alunos, calcular e mostrar: a
soma e a média das mesmas e a menor nota
 */
import java.util.*;

public class notaalunos {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int vet[] = new int[5];
        int soma=0;
        int media=0;
        int menor=0;
        for(int i=0;i<vet.length;i++){
            System.out.println("Digite a nota do aluno "+(i+1));
            vet[i] = in.nextInt();
            soma+=vet[i];
            if(i==0){
                menor=vet[i];
            }
            if(vet[i]<menor){
                menor=vet[i];
            }
        }
        media=soma/vet.length;
        System.out.println("A soma das notas é: "+soma);
        System.out.println("A média das notas é: "+media);
        System.out.println("A menor nota é: "+menor);
    }
}

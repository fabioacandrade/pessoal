import java.util.Scanner;


public class maiorqmedia {
    public static void main(String[] args){
        int vet[] = {1,2,3,4,5,6,7,8,9,10};
        int media=5;
        for(int i=0;i<vet.length;i++){
            if(vet[i]>media){
                System.out.println(vet[i]);
            }
        }
    }

}

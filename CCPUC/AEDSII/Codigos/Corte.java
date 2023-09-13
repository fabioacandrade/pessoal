import java.util.Scanner;

class Corte {
    public static void main(String[] args){ 
        Scanner sc = new Scanner(System.in);
        float num,cutoff;
        while(sc.hasNext()){
            num = sc.nextFloat();
            cutoff = sc.nextFloat();
            float numF = num - (int)num;
            if(numF<cutoff){
                System.out.println((int)num);
            }else
                System.out.println((int)(num+0.5));
        }
        sc.close();
    }
    
}

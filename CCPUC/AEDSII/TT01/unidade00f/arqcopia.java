import java.io.*;

class arqcopia extends Arq{
    public static void main(String[] args){
        Arq.openRead("exercicios.txt");
        String str = "";
        while(Arq.hasNext()){
            str += Arq.readLine() + "\n";
        }
        Arq.close();
        Arq.openWrite("copia.txt");
        Arq.print(str);
        Arq.close();

    }
}
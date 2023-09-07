import java.io.*;

public class Q08{
    //Escreve no arquivo os numeros reais
    public static void ArquivoWrite(int n)throws IOException{
        RandomAccessFile arquivo = new RandomAccessFile("teste2.txt", "rw");

            for(int i=0;i<n;i++){
                double valor = MyIO.readDouble();
                arquivo.writeDouble(valor);
            }

            arquivo.close();
    }
    //Lê e printa na tela os numeros escritos no arquivo
    public static void ArquivoRead()throws IOException{
        RandomAccessFile arquivoRead = new RandomAccessFile("teste2.txt", "r");

            long pos = arquivoRead.length();

            while(pos>0){
                pos-=8;
                arquivoRead.seek(pos);
                double valor = arquivoRead.readDouble(); 
                int x = (int)valor;
                //testando para quando o numero for inteiro
                if(valor-x>0)
                    MyIO.println(valor);
                else 
                    MyIO.println(x);
            }

            arquivoRead.close();
    }
    
    public static void main(String[] args) {
        try {
            MyIO.setCharset("UTF-8");
            int n = MyIO.readInt();
            ArquivoWrite(n);
            ArquivoRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


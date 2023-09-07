import java.io.*;

public class Q8_Arq{
    public static void main(String[] args) {
        try {
            // Cria o arquivo para escrever
            RandomAccessFile file = new RandomAccessFile("valores.txt", "rw");

            // Ler o número de valores reais que serao lidos
            int n = MyIO.readInt();

            for (int i = 0; i < n; i++) {
                double value = MyIO.readDouble();
                file.writeDouble(value);
            }
            file.close();

            // Lendo os valores de trás para frente
            file = new RandomAccessFile("valores.txt", "r");
            long posicaoAtual = file.length();

            while (posicaoAtual > 0) {
                posicaoAtual -= 8; // Tamanho de um double em bytes
                file.seek(posicaoAtual);
                double resp = file.readDouble();
		double decimal = resp - (int)resp; //Calcula a parte decimal do numero
		if(decimal == 0)
		{
			MyIO.println((int)resp);
		}
		else
		{
             		MyIO.println(resp);
		}

            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Q01{
    public static void main(String[] args) {
        while (true) {
            String palavra = MyIO.readLine();
            int tam = palavra.length();
            if(tam==3 && palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'){ //testa o final do algoritmo
                break;
            }
            String resp="SIM";
            for(int i=0;i<tam/2;i++){
                if(palavra.charAt(i)!=palavra.charAt(tam-i-1)) //compara a frase com o contrario dela
                resp="NAO";
                i=tam;
            }
            System.out.println(resp); //printa a resposta
        }
    }
}
import java.util.Random;

class Q04{
    public static void Aleatorio(String frase,char primeiroChar,char segundoChar){
        String fraseMod = "";
        int charAtual;
        for(int i=0;i<frase.length();i++){
            charAtual=frase.charAt(i);          
            if(charAtual==primeiroChar){                //COMPARANBDO SE O CHAR É IGUAL AO MESMO SORTEADO, SE FOR ELE É ADICIONADO A STRING FRASE MOD
                fraseMod+=segundoChar;
            }else
                fraseMod+=frase.charAt(i);            // CASO CONTRARIO, É ADICIONADO O CHAR DA FRASE NORMAL
        }
        MyIO.println(fraseMod);
    }
    public static void main(String[] args){
        char primeiroChar,segundoChar;           // OS DOIS CARACTERES A SEREM SORTEADOS
        int tam;                //TAMANHO DA FRASE
        String palavra = "";
        Random gerador = new Random();
        gerador.setSeed(4);        //SET DA SEED
        do{
            palavra=MyIO.readLine();
            tam=palavra.length();
            primeiroChar=(char)('a' + (Math.abs(gerador.nextInt()) % 26));                // FAZENDO O SORTEIO DOS CARACTERES A CADA REPETIÇÃO
            segundoChar=(char)('a' + (Math.abs(gerador.nextInt()) % 26));
            Aleatorio(palavra, primeiroChar, segundoChar);               //CHAMANDO A FUNÇÃO
        }while(!(tam==3 && palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'));         //TESTANDO O FINAL DO CODIGO
        

    }
}
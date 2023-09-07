
class Q03 {
    public static void main (String[] args){
        while (true) {
            String frase = MyIO.readLine();
            String fraseCifrada = "";
            int tam = frase.length();
            if(tam==3 && frase.charAt(0)=='F' && frase.charAt(1)=='I' && frase.charAt(2)=='M'){ //testa o final do algoritmo
                break;
            }
            char letraCifrada;
            int letra;
            for(int i=0;i<tam;i++){
                letra = ((int)frase.charAt(i) + 3);         // faz o ciframento do caractere
                letraCifrada = (char)letra;
                fraseCifrada+=letraCifrada;          //adiciona o caractere cifrado na string criada anteriormente
            }
            MyIO.println(fraseCifrada);
        }
    }

}

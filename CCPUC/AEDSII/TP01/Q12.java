public class Q12 {
    static String CiframentoRecursivo(String str, int tam,int contador){
        int letra;
        char letraCifrada;
        if(contador==tam){
            return ""; //Indica a parada da recursividade
        }else {
            letra=((int)str.charAt(contador)+3);
            letraCifrada=(char)letra;
            return letraCifrada + CiframentoRecursivo(str, tam, contador + 1);
        }
    }
    public static void main(String[] args){
        while(true){
            String str=MyIO.readLine();
            String strCifrada;
            int tam=str.length();
            if(tam==3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M'){ //testa o final do algoritmo
                break;
            }
            strCifrada=CiframentoRecursivo(str,tam,0);
            MyIO.println(strCifrada);
        }
    }
}

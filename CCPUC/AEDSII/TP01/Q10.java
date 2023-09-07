class Q10{
    //analisa cada caracter e retorna true ou false caso forem iguais
    public static boolean IsPaliRecursivo(String palavra,int esq, int dir){
        boolean resp=true;
        if(esq>=dir){ //define quando o codigo será interrompido
            return true;
        }else{
            if(palavra.charAt(esq)!=palavra.charAt(dir)){
                resp = false;
            }else{
                 resp = resp && IsPaliRecursivo(palavra, esq+1, dir-1);
            }
            return resp;
        }
    }
    public static void main(String[] args){
        while(true){
            String palavra = MyIO.readLine();
            int tam = palavra.length();
            if(tam==3 && palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'){ //testa o final do algoritmo
                break;
            }
            int esq=0;
            int dir=tam-1;
            if(IsPaliRecursivo(palavra,esq,dir)){
                MyIO.println("SIM");
            }else
                MyIO.println("NAO");
            
        }
    }
}
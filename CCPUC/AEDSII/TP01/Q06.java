
public class Q06 {
    //função para verificar se é vogal
    public static boolean isVogal(String palavra,int tam){
        boolean resp=true;
        for(int i=0;i<tam;i++){
            if(palavra.charAt(i)!='a' && palavra.charAt(i)!='e' && palavra.charAt(i)!='i' && palavra.charAt(i)!='o' && palavra.charAt(i)!='u')
                return false;
        }
        return resp;
    }
    //função que verifica se é consoante
    public static boolean isConsoante(String palavra,int tam){
        boolean resp= true;
        for(int i=0;i<tam;i++){
            if((int)palavra.charAt(i)<97 || (int)palavra.charAt(i)>122 || palavra.charAt(i)=='a' || palavra.charAt(i)=='e' || palavra.charAt(i)=='i' || palavra.charAt(i)=='o' || palavra.charAt(i)=='u')
                return false;
        }
        return resp;
    }
    //função que verifica se é inteiro
    public static boolean isInt(String palavra,int tam){
        boolean resp=false;
        for(int i=0;i<tam;i++){
            if((int)palavra.charAt(i)>47 && (int)palavra.charAt(i)<58)
                resp=true;
            else {
                resp=false;
                return resp;
            }
        }
        return resp;
    }
    //função que verifica se é real
    public static boolean isFloat(String palavra,int tam){
        boolean teste1=false;
        boolean teste2=false;
        for(int i=0;i<tam;i++){
            if((int)palavra.charAt(i)>47 && (int)palavra.charAt(i)<58){
                teste1=true;
            }
            if((int)palavra.charAt(i)==44 || (int)palavra.charAt(i)==46){
                teste2=true;
            }
        }
        if (teste1 && teste2){
            return true;
        }else
            return false;
    }
    public static void main(String[] args){
        String palavra ,X1,X2,X3,X4;
        int tam;
        do{
            palavra=MyIO.readLine();
            tam=palavra.length();
            X1=isVogal(palavra,tam) ? "SIM" : "NAO";         //
            X2=isConsoante(palavra,tam) ? "SIM" : "NAO";    //             chama as funções e atribui a string a cada variavel
            X3=isInt(palavra,tam) ? "SIM" : "NAO";          //
            X4=isFloat(palavra,tam) ? "SIM" : "NAO";        //
            MyIO.println(X1+" "+X2+" "+X3+" "+X4);
            
        }while(!(tam==3 && palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'));         //TESTANDO O FINAL DO CODIGO
    }
}

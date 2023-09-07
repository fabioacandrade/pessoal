public class Q15 {

    static boolean isVogalRecursivo(String palavra,int tam, int contador){

        boolean resp=true;
        
        if(contador==tam){ //CONDIÇÃO DE PARADA
            return true;
        
        }else 
            //teste para ver se é uma vogal
            if(palavra.charAt(contador)!='a' && palavra.charAt(contador)!='e' && palavra.charAt(contador)!='i' && palavra.charAt(contador)!='o' && palavra.charAt(contador)!='u')
                resp=false;
            return resp && isVogalRecursivo(palavra, tam, contador+1);
    
    }

    static boolean isConsoanteRecursivo(String palavra,int tam){

        boolean resp=true;
        if(tam==0){ //CONDIÇÃO DE PARADA
            return true;
        //teste para saber se a palavra se encaixa nos parametros adotados
        }else if((int)palavra.charAt(tam-1)<97 || (int)palavra.charAt(tam-1)>122 || palavra.charAt(tam-1)=='i' || palavra.charAt(tam-1)=='o' || palavra.charAt(tam-1)=='u' || palavra.charAt(tam-1)=='a' || palavra.charAt(tam-1)=='e')
            resp=false;
            return resp && isConsoanteRecursivo(palavra, tam-1);
    
    }

    static boolean isIntRecursivo(String palavra,int tam){
        boolean resp=false;
        if(tam==0){ //CONDIÇÃO DE PARADA
            return true;
        }else 
            if((int)palavra.charAt(tam-1)>47 && (int)palavra.charAt(tam-1)<58) //testa para ver se é um numero
                resp=true;
            else 
                return false;
            return resp && isIntRecursivo(palavra, tam-1);
    }

    static boolean isFloatRecursivo(String palavra, int tam, int decimalCount) {
        if (tam == 0) { // CONDIÇÃO DE PARADA
            return decimalCount == 1; // Só pode existir 1 ponto decimal para ser float
        } else if((int)palavra.charAt(tam-1)==44 || (int)palavra.charAt(tam-1)==46) {
            return isFloatRecursivo(palavra, tam - 1, decimalCount + 1);
        } else if((int)palavra.charAt(tam-1)>47 && (int)palavra.charAt(tam-1)<58) {
            return isFloatRecursivo(palavra, tam - 1, decimalCount);
        } else {
            return false; // para todos aqueles que nao sao numericos ou pontos decimais "," "."
        }
    }
    
    
    public static void main(String[] args){
        String palavra ,X1,X2,X3,X4;
        int tam;
        do{
            palavra=MyIO.readLine();
            tam=palavra.length();
            X1=isVogalRecursivo(palavra,tam,0) ? "SIM" : "NAO";         //
            X2=isConsoanteRecursivo(palavra,tam) ? "SIM" : "NAO";    //             chama as funções e atribui a string a cada variavel
            X3=isIntRecursivo(palavra,tam) ? "SIM" : "NAO";          //
            X4=isFloatRecursivo(palavra,tam,0) ? "SIM" : "NAO";        //
            MyIO.println(X1+" "+X2+" "+X3+" "+X4);
            
        }while(!(tam==3 && palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'));         //TESTANDO O FINAL DO CODIGO
    }
    
}

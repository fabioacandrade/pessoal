

class Warmup{
    //recebe dois inteiros e retorna string preechidae
    public static String preencherStr(int n1,int n2){
        String resp = "";
        for(int i=n1;i<=n2;i++){
            resp+=i;
        }
        return resp;
    }
    //lê uma string e retorna o espelho dela
    public static String lerString(String str){
        String resp="";
        int tam=str.length();
        for(int i=1;i<=tam;i++){
            resp+=str.charAt(tam-i);
        }
        return resp;
    }
    public static void main(String[] args){
            String str,strEspelho;
            int n1,n2;
        for(int i=0;i<3;i++){
            n1=MyIO.readInt();
            n2=MyIO.readInt();
            str=preencherStr(n1,n2);
            strEspelho=lerString(str);
            MyIO.println(str+strEspelho);
        }
    }
}
class Q05{
    //pega a expressao e retira todos os espaços
    public static String tiraEspacos(String expressao){
        int tam=expressao.length();
        String nova = "";
        for(int i=0;i<tam;i++){
            if(!(expressao.charAt(i)==' ')){
                nova+=expressao.charAt(i);
            }
        }
        return nova;
    }
    //transforma o char para inteiro
    public static int charToInt(String expressao){
        char nVar = expressao.charAt(0);
        int n=(int)nVar-48;
        return n;
    }
    //utilizada para atribuir aos bools o respectivo char
    public static char atribuicaoBool(int pos,String expressao){
        return expressao.charAt(pos);
    }
    //diminui o tamanho da expressao
    public static String getEquacao(String expressao, int nVariaveis){
        String resp = "";
        if(nVariaveis==2){
            for(int i=3;i<expressao.length();i++){
                resp+=expressao.charAt(i);
            }
        }else{
            for(int i=4;i<expressao.length();i++){
                resp+=expressao.charAt(i);
            }
        }
        return resp;
    }
    //troca todas as ocorrencias de a b e c pelo char correto
    public static String substituiEquacao(char bool1,char bool2,char bool3, String expressao){
        String resp = "";
        for(int i=0;i<expressao.length();i++){
            if(expressao.charAt(i)=='A'){
                resp+=bool1;
            }else if(expressao.charAt(i)=='B'){
                resp+=bool2;
            }else if(expressao.charAt(i)=='C'){
                resp+=bool3;
            }else
                resp+=expressao.charAt(i);
        }
        return resp;
    }
    //troca a expressao até o tamanho ser 2 ou menor
    public static String trocaFinal(String expressao) 
        {
                do{
                 expressao = expressao.replace("not(1)","0");
                 expressao = expressao.replace("not(0)","1");

                 expressao = expressao.replace("and(1)","1");
                 expressao = expressao.replace("and(0)","0");

                 expressao = expressao.replace("or(1)","1");
                 expressao = expressao.replace("or(0)","0");

                 expressao = expressao.replace("and(0,0","and(0");
                 expressao = expressao.replace("and(0,1","and(0");
                 expressao = expressao.replace("and(1,0","and(0");
                 expressao = expressao.replace("and(1,1","and(1");

                 expressao = expressao.replace("or(0,0","or(0");
                 expressao = expressao.replace("or(0,1","or(1");
                 expressao = expressao.replace("or(1,0","or(1");
                 expressao = expressao.replace("or(1,1","or(1");


                }while(expressao.length() > 2);
                return expressao;
        }
    public static void main(String[] args){
        do{
            String expressao = "";
            int nVariaveis;
            char bool1=' ',bool2=' ',bool3=' ';

            expressao=MyIO.readLine();
            if(expressao.length()==1){
                break;
            }
            expressao=tiraEspacos(expressao);
            nVariaveis=charToInt(expressao);
            if(nVariaveis==2){
                bool1=atribuicaoBool(1,expressao);
                bool2=atribuicaoBool(2,expressao);
            }else if(nVariaveis==3){
                bool1=atribuicaoBool(1,expressao);
                bool2=atribuicaoBool(2,expressao);
                bool3=atribuicaoBool(3,expressao);
            }
            expressao=getEquacao(expressao,nVariaveis);
            expressao=substituiEquacao(bool1,bool2,bool3,expressao);
            expressao=trocaFinal(expressao);
            MyIO.println(expressao);
        }while(true);
    }
}
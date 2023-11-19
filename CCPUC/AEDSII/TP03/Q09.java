import java.io.*;

class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
        this(0, null, null, null, null);
     }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}

class Matriz {
   private Celula inicio;
   private int linha, coluna;

   public Matriz (){
      Matriz(3, 3);
   }

   public Matriz(int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;
      //alocar a matriz com this.linha linhas e this.coluna colunas
   }


   public Matriz soma (Matriz m) {
     Matriz resp = null;

     if(this.linha == m.linha && this.coluna == m.coluna){
        resp = new Matriz(this.linha, this.coluna);
        for(int i=0;i<this.linha;i++){
            for(int j=0;j<this.coluna;j++){
                resp.inicio.elemento = this.inicio.elemento + m.inicio.elemento;
                resp.inicio = resp.inicio.dir;
                m.inicio = m.inicio.dir;
            }
            resp.inicio = resp.inicio.inf;
            m.inicio = m.inicio.inf;
        }

      return resp;
   }

   public Matriz multiplicacao (Matriz m) {
      Matriz resp = null;

      if(){
         //...
      }

      return resp;
   }

   public boolean isQuadrada(){
        return (this.linha == this.coluna);
   }

   public void mostrarDiagonalPrincipal (){
      if(isQuadrada() == true){

      }
   }

   public void mostrarDiagonalSecundaria (){
      if(isQuadrada() == true){
      }
   }
}




class Q06{
    
    public static void preencheArray(int[] jogadores){
        Arq arq = new Arq();

        String str;

        //arq.openRead("players.csv");
        arq.openRead("players.csv");

        str = arq.readLine(); //pulando a primeira linha


        //preenchendo o array
        for(int i=0;i<3922;i++){
            jogadores[i] = new int();
            str = arq.readLine();
            jogadores[i].ler(str);
        }

        arq.close();
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int[] jogadores = new int[3922];
 
        preencheArray(jogadores);

        
    }
}
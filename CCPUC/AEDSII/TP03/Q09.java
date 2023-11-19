import java.util.Scanner;

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
      this(3, 3);
   }

   public Matriz(int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;

      //alocando a matriz com this.linha linhas e this.coluna colunas
      Celula tmp = new Celula();
      inicio = tmp;
      Celula tmp2 = inicio;

      for (int j = 1; j < coluna; j++) {
         tmp.dir = new Celula();
         tmp.dir.esq = tmp;
         tmp = tmp.dir;
      }

      for (int i = 1; i < linha; i++) {
         tmp2.inf = new Celula();
         tmp2.inf.sup = tmp2;
         tmp2 = tmp2.inf;
         tmp = tmp2;

         for (int j = 1; j < coluna; j++) {
               tmp.dir = new Celula();
               tmp.dir.esq = tmp;
               tmp = tmp.dir;
               tmp.sup = tmp.esq.sup.dir;
               tmp.sup.inf = tmp;
         }
      }
   }

   public boolean isQuadrada(){
        return (this.linha == this.coluna);
   }

   public void mostrar(){
      Celula tmp = inicio;
      for(int i = 0; i < linha; i++, tmp = tmp.inf){ // Percorre a matriz
         Celula tmp2 = tmp;
         for(int j = 0; j < coluna; j++, tmp2 = tmp2.dir){
            System.out.print(tmp2.elemento + " "); // Mostra o elemento
         }
         System.out.println();
      }
   }


   public void inserir(int x, int linha, int coluna){
      Celula linhaM, colunaM;
      linhaM = colunaM = this.inicio;
      for(int i = 0; i < linha; i++, linhaM = linhaM.inf);
      colunaM = linhaM;
      for(int i = 0; i < coluna; i++, colunaM = colunaM.dir);
      colunaM.elemento = x;
   }

   public void mostrarDiagonalPrincipal(){
      if(isQuadrada()){
         Celula tmp = inicio;
         for(int i = 0; i < linha; i++, tmp = tmp.inf){ // Percorre a matriz
            System.out.print(tmp.elemento + " "); // Mostra o elemento
         }
         System.out.println();
      } else {
         System.out.println("Erro ao mostrar diagonal principal!");
      }
   }

   void mostrarDiagonalSecundaria() {
      Celula tmpThis, tmpResp;
      tmpThis = tmpResp = inicio;

      for (int i = 0; i < linha; i++) {
         for (int j = 0; j < coluna; j++) {
            if (i + j == linha - 1) // Verifica se a célula está na diagonal secundária
               System.out.print(tmpThis.elemento + " "); // Mostra o elemento
            tmpThis = tmpThis.dir; // Move para a próxima célula da mesma linha
         }
         tmpResp = tmpResp.inf; // Move para a próxima linha
         tmpThis = tmpResp; // Atualiza a célula atual para a primeira célula da nova linha
      }
      System.out.println();
   }

   public Matriz soma(Matriz m2){
      Celula linha1,linha2,coluna1,coluna2;
      linha1 = coluna1 = this.inicio;
      linha2 = coluna2 = m2.inicio;
      int soma = 0;
      Matriz resp = new Matriz(this.linha,this.coluna);

      for(int i=0;i<this.linha;i++){
         for(int j=0;j<this.coluna;j++){
            if (coluna1 != null && coluna2 != null) {
               soma = coluna1.elemento + coluna2.elemento;
               resp.inserir(soma,i,j);
               coluna1 = coluna1.dir;
               coluna2 = coluna2.dir;
            }
         }
         if (linha1 != null && linha2 != null) {
            linha1 = linha1.inf;
            linha2 = linha2.inf;
            coluna1 = linha1;
            coluna2 = linha2;
         }
      }

      return resp;
   }

   public Matriz multiplicacao(Matriz m2){
      Matriz resp = null;

      if(this.coluna == m2.linha){
         resp = new Matriz(this.linha, m2.coluna);
         Celula tmpThis = this.inicio;
         Celula tmpM2 = m2.inicio;
         Celula tmpResp = resp.inicio;

         for(int i = 0; i < this.linha; i++){
            for(int j = 0; j < m2.coluna; j++){
               int soma = 0;
               for(int k = 0; k < this.coluna; k++){
                  soma += tmpThis.elemento * tmpM2.elemento;
                  tmpThis = tmpThis.dir;
                  tmpM2 = tmpM2.inf;
               }
               tmpResp.elemento = soma;
               tmpResp = tmpResp.dir;
               tmpThis = tmpThis.esq;
               tmpM2 = m2.inicio;
            }
            tmpThis = tmpThis.inf;
            tmpM2 = m2.inicio;
            tmpResp = tmpResp.inf;
         }
      }

      return resp;
   }

}




class Q09{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();

        for(int i = 0; i < count; i++){
            int linha = sc.nextInt();
            int coluna = sc.nextInt();

            Matriz m1 = new Matriz(linha, coluna);

            for(int j = 0; j < linha; j++){
                for(int k = 0; k < coluna; k++){
                    m1.inserir(sc.nextInt(), j, k);
                }
            }

            linha = sc.nextInt();
            coluna = sc.nextInt();

            Matriz m2 = new Matriz(linha, coluna);

            for(int j = 0; j < linha; j++){
                for(int k = 0; k < coluna; k++){
                    m2.inserir(sc.nextInt(), j, k);
                }
            }

            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();

            Matriz soma = m1.soma(m2);

            soma.mostrar();

            Matriz multiplicacao = m1.multiplicacao(m2); 

            multiplicacao.mostrar();
        }

    }
}
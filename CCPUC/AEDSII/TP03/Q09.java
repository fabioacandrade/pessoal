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

   public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        inicio = new Celula();

        Celula aux = inicio;
        for (int i = 0; i < linha; i++) {
            Celula rowAux = aux;
            for (int j = 0; j < coluna; j++) {
                rowAux.dir = new Celula(0);
                rowAux.dir.esq = rowAux;
                rowAux.dir.sup = (i > 0) ? rowAux.sup.dir : null;
                if (rowAux.dir.sup != null) {
                    rowAux.dir.sup.inf = rowAux.dir;
                }
                rowAux = rowAux.dir;
            }
            aux.inf = new Celula();
            aux.inf.sup = aux;
            aux = aux.inf;
        }
    }

   public Matriz() {
        this.coluna = 0;
        this.linha = 0;
        this.inicio = null;
    }

   public Celula getCelula(int linha, int coluna) {
      Celula resp = inicio;
      for (int i = 0; i < linha; i++, resp = resp.inf);
      for (int j = 0; j < coluna; j++, resp = resp.dir);
      return resp;
   }

   public void inserir(int x, int linha, int coluna) {
      Celula aux = getCelula(linha, coluna);
      aux.elemento = x;
   }

   public void mostrar() {
      Celula aux = inicio;
      for (int i = 0; i < linha; i++) {
            Celula aux1 = aux;
            for (int j = 0; j < coluna; j++) {
               System.out.print(aux1.elemento + " ");
               aux1 = aux1.dir;
            }
            System.out.println();
            aux = aux.inf;
      }
   }

   public Matriz soma(Matriz m2) {
      Matriz resp = new Matriz(linha, coluna);
      Celula aux = inicio;
      Celula aux2 = m2.inicio;
      for (int i = 0; i < linha; i++) {
         Celula aux1 = aux;
         Celula aux3 = aux2;
         for (int j = 0; j < coluna; j++) {
            if(aux1 != null && aux3 != null){
               resp.inserir(aux1.elemento + aux3.elemento, i, j);
               aux1 = aux1.dir;
               aux3 = aux3.dir;
            }
         }
         if(aux != null && aux2 != null){
            aux = aux.inf;
            aux2 = aux2.inf;
         }
      }
      return resp;
   }

   public Matriz multiplicacao(Matriz m2) {
        Matriz resp = new Matriz(linha, m2.coluna);
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < m2.coluna; j++) {
                int value = 0;
                for (int k = 0; k < coluna; k++) {
                    value += getCelula(i, k).elemento * m2.getCelula(k, j).elemento;
                }
                resp.inserir(value, i, j);
            }
        }
        return resp;
    }
    
   public void mostrarDiagonalPrincipal() {
        Celula aux = inicio;
        while (aux != null) {
            System.out.print(aux.elemento + " ");
            aux = aux.inf != null ? aux.inf.dir : null;
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        Celula aux = inicio;
        for (int i = 0; i < linha - 1; i++) {
            aux = aux.inf;
        }
        while (aux != null) {
            System.out.print(aux.elemento + " ");
            aux = aux.esq != null ? aux.esq.inf : null;
        }
        System.out.println();
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
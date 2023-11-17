class Jogador{

    int id;
    int altura;
    int peso;
    int anoNascimento;
    String nome;
    String cidadeNascimento;
    String estadoNascimento;
    String universidade;

    
    Jogador(){
        id = 0;
        nome = "";
        altura = 0;
        peso = 0;
        anoNascimento = 0;
        cidadeNascimento = "";
        estadoNascimento = "";
        universidade = "";
    }

    Jogador(int id, int altura, int peso, String universidade, int anoNascimento, String nome, String cidadeNascimento, String estadoNascimento){
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getAltura(){
        return altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int peso){
        this.peso = peso;
    }

    public int getAnoNascimento(){
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento){
        this.anoNascimento = anoNascimento;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCidadeNascimento(){
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento){
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento(){
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento){
        this.estadoNascimento = estadoNascimento;
    }

    public String getUniversidade(){
        return universidade;
    }

    public void setUniversidade(String universidade){
        this.universidade = universidade;
    }

    public void imprimir(){
        System.out.println("["+id+"] ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNascimento+" ## "+universidade+" ## "+cidadeNascimento+" ## "+estadoNascimento+" ##");
    }

    public void ler(String str){
        String[] array = str.split(",",8);
        this.id = Integer.parseInt(array[0]);
        this.nome = array[1];
        this.altura = Integer.parseInt(array[2]);
        this.peso = Integer.parseInt(array[3]);
        this.universidade = array[4].isEmpty() ? "nao informado" : array[4];
        this.anoNascimento = Integer.parseInt(array[5]);
        this.cidadeNascimento = array[6].isEmpty() ? "nao informado" : array[6];
        this.estadoNascimento = array[7].isEmpty() ? "nao informado" : array[7];
        
    }

    public void clone(Jogador Clonando){
        //if (Clonando != null) {
            this.id = Clonando.getId();
            this.nome = Clonando.getNome();
            this.altura = Clonando.getAltura();
            this.peso = Clonando.getPeso();
            this.anoNascimento = Clonando.getAnoNascimento();
            this.cidadeNascimento = Clonando.getCidadeNascimento();
            this.estadoNascimento = Clonando.getEstadoNascimento();
            this.universidade = Clonando.getUniversidade();
        //}
    }

}

class Celula{
    public Jogador elemento;
    public Celula prox;

    public Celula(){
        this(null);
    }

    public Celula(Jogador elemento){
        this.elemento = elemento;
        this.prox = null;
    }

}


class ListaFlexivel{
    private Celula primeiro;
    private Celula ultimo;

    public ListaFlexivel(){
        primeiro = new Celula();
        ultimo = primeiro;
        
    }

    public ListaFlexivel(int tamanho){
        primeiro = new Celula();
        ultimo = primeiro;
        
    }


    public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

    public void inserirInicio(Jogador jogador){
        Celula tmp = new Celula(jogador);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Jogador jogador){
        ultimo.prox = new Celula(jogador);
        ultimo = ultimo.prox;
    }

    public void inserir(Jogador jogador, int pos){
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho){
            System.out.println("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        }else if(pos == 0){
            inserirInicio(jogador);
        }else if(pos == tamanho){
            inserirFim(jogador);
        }else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(jogador);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public Jogador removerInicio(){
        if(primeiro == ultimo){
            System.out.println("Erro ao remover (vazia)!");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public Jogador removerFim(){
        if(primeiro == ultimo){
            System.out.println("Erro ao remover (vazia)!");
        }
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);
        Jogador resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return resp;
    }

    public Jogador remover(int pos){
        Jogador resp = null;
        int tamanho = tamanho();
        if(primeiro == ultimo || pos < 0 || pos >= tamanho){
            System.out.println("Erro ao remover (" + pos + " / " + tamanho + " nao existe)!");
        }else if(pos == 0){
            resp = removerInicio();
        }else if(pos == tamanho - 1){
            resp = removerFim();
        }else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        return resp;
    
    }

    public void mostrar(){
        int j=0;
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            i.elemento.imprimir();
            j++;
        }
    }

    public void setId(){
        int j=0;
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            i.elemento.setId(j);
            j++;
        }
    }
    
}

class Q05{
    
    public static void preencheArray(Jogador[] jogadores){
        Arq arq = new Arq();

        String str;

        arq.openRead("/tmp/players.csv");

        str = arq.readLine(); //pulando a primeira linha


        //preenchendo o array
        for(int i=0;i<3922;i++){
            jogadores[i] = new Jogador();
            str = arq.readLine();
            jogadores[i].ler(str);
        }

        arq.close();
    }
    public static void main(String[] args){

        Jogador[] jogadores = new Jogador[3922];


        preencheArray(jogadores);

        ListaFlexivel lista = new ListaFlexivel(3922);

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            lista.inserirFim(jogadores[Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        int count = Integer.parseInt(entrada);
        int id=0;
        Jogador aux = new Jogador();

        for(int i=0;i<count;i++){

            String[] array = MyIO.readLine().split(" ");

            if(array.length==2){
                id = Integer.parseInt(array[1]);

            }else if(array.length==3){
                id = Integer.parseInt(array[2]);
            }

            if(array[0].equals("II")){
                aux.clone(jogadores[id]);
                lista.inserirInicio(aux);
            }
            else if(array[0].equals("IF")){
                aux.clone(jogadores[id]);
                lista.inserirFim(aux);
            }
            else if(array[0].equals("I*")){
                aux.clone(jogadores[id]);
                lista.inserir(aux,Integer.parseInt(array[1]));
            }

            else if(array[0].equals("RI")){
                Jogador removido = lista.removerInicio();
                MyIO.println("(R) "+ removido.getNome());
            }

            else if(array[0].equals("RF")){
                Jogador removido = lista.removerFim();
                MyIO.println("(R) "+ removido.getNome());
            }

            else if(array[0].equals("R*")){
                Jogador removido = lista.remover(Integer.parseInt(array[1]));
                MyIO.println("(R) "+ removido.getNome());
            }

        }
        //consertando o id
        lista.setId();
        
        lista.mostrar();
    }
}
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
        System.out.println("["+id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNascimento+" ## "+universidade+" ## "+cidadeNascimento+" ## "+estadoNascimento+"]");
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
        this.id = Clonando.getId();
        this.nome = Clonando.getNome();
        this.altura = Clonando.getAltura();
        this.peso = Clonando.getPeso();
        this.anoNascimento = Clonando.getAnoNascimento();
        this.cidadeNascimento = Clonando.getCidadeNascimento();
        this.estadoNascimento = Clonando.getEstadoNascimento();
        this.universidade = Clonando.getUniversidade();
    }
}




class Q09{
    public static int countComparacoes = 0, countMovimentacoes = 0;
    
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

    public static void swap(Jogador jog1, Jogador jog2){
        Jogador temp = new Jogador();
        temp.clone(jog1);
        jog1.clone(jog2);
        jog2.clone(temp);
        countMovimentacoes += 3;
    }

    public static int getMaiorFilho(int i, int tam, Jogador[] playersClone) {
        int filho;
        countComparacoes++;
        if (2 * i == tam || playersClone[2 * i].getAltura() > playersClone[2 * i + 1].getAltura()
                || (playersClone[2 * i].getAltura() == playersClone[2 * i + 1].getAltura()
                    && playersClone[2 * i].getNome().compareTo(playersClone[2 * i + 1].getNome()) > 0)) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    public static void reconstruir(int tamHeap, Jogador[] playersClone) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap, playersClone);
            countComparacoes++;
            if (playersClone[i].getAltura() < playersClone[filho].getAltura()
                    || (playersClone[i].getAltura() == playersClone[filho].getAltura()
                        && playersClone[i].getNome().compareTo(playersClone[filho].getNome()) < 0)) {
                swap(playersClone[i], playersClone[filho]);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public static void construir(Jogador[] playersClone, int tamHeap) {
        countComparacoes++;
        for (int i = tamHeap; i > 1 && (playersClone[i].getAltura() > playersClone[i / 2].getAltura() 
                    || (playersClone[i].getAltura() == playersClone[i / 2].getAltura()
                        && playersClone[i].getNome().compareTo(playersClone[i / 2].getNome()) > 0)); i /= 2) {
            swap(playersClone[i], playersClone[i/2]);
        }
    }

    public static void heapsort(Jogador[] playersClone, int n) {
        //Alterar o vetor ignorando a posicao zero
        Jogador[] playersClone2 = new Jogador[n+1];
        for (int i = 0; i < n; i++) {
            playersClone2[i+1] = playersClone[i];
        }
        playersClone = playersClone2;

        //Contrucao do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(playersClone, tamHeap);
        }

        //Ordenacao propriamente dita
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(playersClone[1], playersClone[tamHeap--]);
            reconstruir(tamHeap, playersClone);
        }

        //movendo uma casa para esquerda
        for (int i = 0; i < n; i++) {
            playersClone[i] = playersClone[i+1];
            countMovimentacoes++;
        }
    }



    
    public static void main(String[] args){
        int countCopia = 0;
        long tempoInicial;

        Jogador[] jogadores = new Jogador[3922]; //array de jogadores

        Jogador[] copia = new Jogador[3922];

        preencheArray(jogadores);

        String entrada = MyIO.readLine();

        //preenchendo o array copia 
        while(!entrada.equals("FIM")){
            int id = Integer.parseInt(entrada);
            copia[countCopia] = new Jogador();
            copia[countCopia].clone(jogadores[id]);
            entrada = MyIO.readLine();
            countCopia++;
        }

        tempoInicial = System.currentTimeMillis();
        heapsort(copia,countCopia);


        for(int i=0;i<copia.length && copia[i]!=null;i++){
            copia[i].imprimir();
        }



        Arq matricula = new Arq();
        matricula.openWrite("matricula_heapsort.txt");
        matricula.print("808674\t" + countComparacoes + "\t" + countMovimentacoes + "\t" + (System.currentTimeMillis()-tempoInicial)/1000.0 );
        matricula.close();
        

    }
}
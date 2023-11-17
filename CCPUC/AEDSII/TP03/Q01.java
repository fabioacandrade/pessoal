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


class Lista{
    private Jogador[] array;
    private int n;

    public Lista (){
        this(3922);
    }

    public Lista (int tamanho){
        array = new Jogador[tamanho];
        n = 0;
        for(int i = 0; i < tamanho; i++){
            array[i] = new Jogador();
        }
    }

    
    public void inserirInicio(Jogador jogador){
        for(int i=n;i>0;i--){
            array[i].clone(array[i-1]);
        }
        array[0].clone(jogador);
        n++;
    }

    public void inserirFim(Jogador jogador){
        array[n].clone(jogador);
        n++;
    }

    public void inserir(Jogador jogador, int pos){
        for(int i=n;i>pos;i--){
            array[i].clone(array[i-1]);
        }
        array[pos].clone(jogador);
        n++;
    }

    public Jogador removerInicio(){
        Jogador resp = new Jogador();
        resp.clone(array[0]);
        n--;
        for(int i=0;i<n;i++){
            array[i].clone(array[i+1]);
        }
        return resp;
    }

    public Jogador removerFim(){
        Jogador resp = new Jogador();
        resp.clone(array[n-1]);
        n--;
        return resp;
    }

    public Jogador remover(int pos){
        Jogador resp = new Jogador();
        resp.clone(array[pos]);
        n--;
        for(int i=pos;i<n;i++){
            array[i].clone(array[i+1]);
        }
        return resp;
    }

    public void mostrar(){
        for(int i=0;i<n;i++){
            array[i].imprimir();
        }
    }

    public void setId(){
        int id=0;
        for(int i=0;i<n;i++){
            array[i].setId(id);
            id++;
        }
    }

}

class Q01{
    
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

        Lista resp = new Lista();

        preencheArray(jogadores);

        Lista lista = new Lista(3922);

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
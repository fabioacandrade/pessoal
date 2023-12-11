import java.util.Scanner;
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

class No{
    public Jogador elemento;
    public No esq, dir;

    No(Jogador elemento){
        this(elemento,null,null);
    }

    No(Jogador elemento, No esq, No dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria{
    private No raiz;
    private int comparacoes;

    ArvoreBinaria(){
        raiz = null;
        comparacoes = 0;
    }

    public void inserir(Jogador x){
        raiz = inserir(x,raiz);
    }

    private No inserir(Jogador x, No i){
        if(i == null){
            comparacoes++;
            i = new No(x);
        }else if(x.getNome().compareTo(i.elemento.getNome()) < 0){
            comparacoes++;
            i.esq = inserir(x,i.esq);
        }else if(x.getNome().compareTo(i.elemento.getNome()) > 0){
            comparacoes++;
            i.dir = inserir(x,i.dir);
        }else{
            System.out.println("Erro ao inserir!");
        }
        return i;
    }

    //metodo pesquisar mostrando o caminho percorrido (esq, dir, raiz)
    public void pesquisar(String x){
        System.out.print(x+" raiz ");
        pesquisar(x,raiz);
    }

    private void pesquisar(String x, No i){
        if(i == null){
            comparacoes++;
            System.out.println("NAO");
        }else if(x.compareTo(i.elemento.getNome()) < 0){
            comparacoes++;
            System.out.print("esq ");
            pesquisar(x,i.esq);
        }else if(x.compareTo(i.elemento.getNome()) > 0){
            comparacoes++;
            System.out.print("dir ");
            pesquisar(x,i.dir);
        }else{
            System.out.println("SIM");
        }
    }

    public void mostrarCentral(){
        mostrarCentral(raiz);
    }

    private void mostrarCentral(No i){
        if(i != null){
            mostrarCentral(i.esq);
            System.out.println(i.elemento.getNome());
            mostrarCentral(i.dir);
        }
    }

    public void mostrarPre(){
        mostrarPre(raiz);
    }

    private void mostrarPre(No i){
        if(i != null){
            System.out.println(i.elemento.getNome());
            mostrarPre(i.esq);
            mostrarPre(i.dir);
        }
    }

    public void mostrarPos(){
        mostrarPos(raiz);
    }

    private void mostrarPos(No i){
        if(i != null){
            mostrarPos(i.esq);
            mostrarPos(i.dir);
            System.out.println(i.elemento.getNome());
        }
    }

    public int getComparacoes(){
        return comparacoes;
    }

    public void setComparacoes(int comparacoes){
        this.comparacoes = comparacoes;
    }

    public void swap(int menor, int i, Jogador[] jogadores){
        Jogador temp = jogadores[menor];
        jogadores[menor] = jogadores[i];
        jogadores[i] = temp;
    }

}


class Q01{
    
    public static void preencheArray(Jogador[] jogadores){
        Arq arq = new Arq();

        String str;

        //arq.openRead("players.csv");
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

        Scanner sc = new Scanner(System.in);

        Jogador[] jogadores = new Jogador[3922];
 
        preencheArray(jogadores);

        ArvoreBinaria arvore = new ArvoreBinaria();

        String str;

        str = sc.nextLine();
        
        long start = System.currentTimeMillis();

        while(!str.equals("FIM")){
            arvore.inserir(jogadores[Integer.parseInt(str)]);
            str = sc.nextLine();
        }

        str = sc.nextLine();

        while(!str.equals("FIM")){
            arvore.pesquisar(str);
            str = sc.nextLine();
        }
        
        long end = System.currentTimeMillis();

        Arq.openWrite("808674_arvoreBinaria.txt");
        Arq.print("808674\t"+"\t"+arvore.getComparacoes()+"\t"+(end-start)/1000.0);

    }
}
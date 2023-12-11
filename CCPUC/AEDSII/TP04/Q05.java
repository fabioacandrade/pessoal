

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

    No(){
        this(new Jogador());
    }

    No(Jogador elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}
     
class ArvoreBinaria{
    private No raiz;
    public int comparacoes;

    ArvoreBinaria(){
        raiz = null;
        comparacoes = 0;
    }

    public void inserir(Jogador x){
        raiz = inserir(x, raiz);
    }

    private No inserir(Jogador x, No i){
        if(i == null){
            i = new No(x);
            comparacoes++;
        }else if(x.getNome().compareTo(i.elemento.getNome()) < 0){
            comparacoes++;
            i.esq = inserir(x, i.esq);
        }else if(x.getNome().compareTo(i.elemento.getNome()) > 0){
            comparacoes++;
            i.dir = inserir(x, i.dir);
        }else{
            System.out.println("Erro ao inserir!");
        }
        return i;
    }

    public void treeSort(Jogador[] jogadores){
        treeSort(jogadores, raiz);
    }

    private void treeSort(Jogador[] jogadores, No i){
        if(i != null){
            treeSort(jogadores, i.esq);
            System.out.println(i.elemento.getNome());
            treeSort(jogadores, i.dir);
        }
    }
}




class Q05{
    
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
        long start = System.currentTimeMillis();

        Jogador[] jogadores = new Jogador[3922];
        preencheArray(jogadores);

        Jogador[] jogadoresClone = new Jogador[3922];
        int count = 0;

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            jogadoresClone[count] = jogadores[Integer.parseInt(entrada)];
            count++;
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            entrada = MyIO.readLine();
        }

        ArvoreBinaria arvore = new ArvoreBinaria();

        for(int i=0;i<count;i++){
            arvore.inserir(jogadoresClone[i]);
        }

        arvore.treeSort(jogadores);

        long end = System.currentTimeMillis();

        Arq.openWrite("808674_treesort.txt");
        Arq.print("808674\t"+"\t"+arvore.comparacoes+"\t"+(end-start)/1000.0);
    }
}

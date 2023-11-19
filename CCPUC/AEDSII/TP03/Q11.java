
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

class Celula{
    Jogador elemento;
    Celula prox, ant;

    Celula(){
        this(null);
    }

    Celula(Jogador elemento){
        this.elemento = elemento;
        this.prox = this.ant = null;
    }
}

class ListaDupla{
    Celula primeiro, ultimo;
    int comparacoes;
    int movimentacoes;

    ListaDupla(){
        primeiro = new Celula();
        ultimo = primeiro;
        comparacoes = 0;
        movimentacoes = 0;
    }

    void inserirInicio(Jogador jogador){
        Celula tmp = new Celula(jogador);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    void inserirFim(Jogador jogador){
        ultimo.prox = new Celula(jogador);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    void swap(Celula i, Celula j){
        Jogador tmp = new Jogador();
        tmp.clone(i.elemento);
        i.elemento.clone(j.elemento);
        j.elemento.clone(tmp);
    }

    void quicksort(){
        quicksort(primeiro.prox, ultimo);
    }

    //quicksort com o estado como comparação e em caso de empate utilizando o nome
    void quicksort(Celula esq, Celula dir){
        Celula i = esq, j = dir;
        Celula pivo = esq;
        while(i.ant != j && i.ant != j.prox){
            comparacoes++;
            while(i.elemento.estadoNascimento.compareTo(pivo.elemento.estadoNascimento) < 0 || (i.elemento.estadoNascimento.compareTo(pivo.elemento.estadoNascimento) == 0 && i.elemento.nome.compareTo(pivo.elemento.nome) < 0)){
                i = i.prox;
                comparacoes++;
            }
            while(j.elemento.estadoNascimento.compareTo(pivo.elemento.estadoNascimento) > 0 || (j.elemento.estadoNascimento.compareTo(pivo.elemento.estadoNascimento) == 0 && j.elemento.nome.compareTo(pivo.elemento.nome) > 0)){
                j = j.ant;
                comparacoes++;
            }
            if(i.ant != j && i.ant != j.prox){
                swap(i, j);
                i = i.prox;
                j = j.ant;
                movimentacoes += 3;
                comparacoes++;
            }
        }
        if(esq != j && esq.ant != j){
            quicksort(esq, j);
            comparacoes++;
        }
        if(i != dir && dir.prox != i){
            quicksort(i, dir);
            comparacoes++;
        }
    }

    void mostrar(){
        Celula i = primeiro.prox;
        while(i != null){
            i.elemento.imprimir();
            i = i.prox;
        }
    }
}


class Q11{
    
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

        ListaDupla lista = new ListaDupla();

        String str;

        str = sc.nextLine();

        while(!str.equals("FIM")){
            lista.inserirFim(jogadores[Integer.parseInt(str)]);
            str = sc.nextLine();
        }

        float start = System.currentTimeMillis();
        lista.quicksort();
        float end = System.currentTimeMillis();

        lista.mostrar();

        Arq.openWrite("808674_quicksort2.txt");
        Arq.print("808674\t"+"\t"+lista.comparacoes+"\t"+lista.movimentacoes+"\t"+(end-start)/1000.0);

    }
}
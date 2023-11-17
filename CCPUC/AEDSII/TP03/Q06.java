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


class PilhaFlexivel{
    private Celula topo;
    private int n;

    public PilhaFlexivel(){
        topo = null;
        n = 0;
    }

    public void inserir(Jogador x){
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
        n++;
    }

    public Jogador remover(){
        Jogador resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        n--;
        return resp;
    }

    public void mostrar() {
		mostrar(topo);
	}

	private void mostrar(Celula i) {
		if (i != null) {
			mostrar(i.prox);
			i.elemento.imprimir();
		}
	}
    public void setId(){
        setId(topo,n-1);
    }
    //colocando os ids inversos
    public void setId(Celula i,int n){
        if(i != null){
            setId(i.prox,n-1);
            i.elemento.setId(n);
        }
    }

    public int getN(){
        return n;
    }
}

class Q06{
    
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

        PilhaFlexivel pilha = new PilhaFlexivel();

        String entrada = sc.nextLine();

        while(!entrada.equals("FIM")){
            pilha.inserir(jogadores[Integer.parseInt(entrada)]);
            entrada = sc.nextLine();
        }

        int count = sc.nextInt();
        Jogador aux = new Jogador();

        for(int i=0;i<count+1;i++){

            String[] array = sc.nextLine().split(" ");

            if(array[0].equals("I")){
                pilha.inserir(jogadores[Integer.parseInt(array[1])]);
            }else if(array[0].equals("R")){
                aux = pilha.remover();
                System.out.println("(R) "+aux.getNome());
            }
        }
        //consertando o id
        pilha.setId();
        
        pilha.mostrar();
        sc.close();
    }
}
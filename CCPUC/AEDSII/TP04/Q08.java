
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

class HashRehash{
    Jogador tabela[];
    int m;
    public int comparacoes;

    HashRehash(){
        this(25);
    }

    HashRehash(int m){
        this.m = m;
        tabela = new Jogador[m];
        for(int i=0;i<m;i++){
            tabela[i] = null;
        }
    }

    public int h(Jogador elemento){
        return elemento.getAltura() % m;
    }

    public int reh(Jogador elemento){
        return (elemento.getAltura()+1) % m;
    }

    public boolean inserir(Jogador elemento){
        boolean resp = false;

        if(elemento != null){
            int pos = h(elemento);
            comparacoes++;
            if(tabela[pos] == null){
                tabela[pos] = elemento;
                resp = true;
            }else{
                pos = reh(elemento);
                comparacoes++;
                if(tabela[pos] == null){
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }

        return resp;
    }

    public boolean pesquisar(String elemento){
        boolean resp = false;
        for(int i=0;i<m;i++){
            comparacoes++;
            if(tabela[i] != null){
                comparacoes++;
                if(tabela[i].getNome().equals(elemento)){
                    resp = true;
                    i = m;
                }
            }
        }
        return resp;
    }
}


class Q08{
    
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

        HashRehash hash = new HashRehash();

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            hash.inserir(jogadores[Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            System.out.print(entrada+" ");
            if(hash.pesquisar(entrada)){
                System.out.println("SIM");
            }else{
                System.out.println("NAO");
            }
            entrada = MyIO.readLine();
        }
        

        long end = System.currentTimeMillis();

        Arq.openWrite("808674_hashRehash.txt");
        Arq.print("808674\t"+"\t"+hash.comparacoes+"\t"+(end-start)/1000.0);
        Arq.close();
    }
}

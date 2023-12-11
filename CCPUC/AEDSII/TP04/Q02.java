/*Nessa estrutura, temos uma árvore binária tradicional na qual cada nó tem um ponteiro para outra árvore binária. Graficamente, a primeira árvore está no plano xy e a árvore de seus nós pode ser imaginada no espaço tridimensional. Temos dois tipos de nós.


O primeiro tem um número inteiro como chave, os ponteiros esq e dir (ambos para nós do primeiro tipo) e um ponteiro para nós do segundo tipo. O outro nó tem uma String como chave e os ponteiros esq e dir (ambos para nós do segundo tipo). A chave de pesquisa da primeira árvore é o atributo altura mod 15 e, da outra, é o atributo Nome do Jogador. Conforme a figura abaixo.


Destaca-se que nossa pesquisa faz um mostrar na primeira árvore e um mostrar na segunda. Faremos um mostrar na primeira árvore porque ela é organizada pelo altura mod 15, permitindo que o valor desejado esteja na segunda árvore de qualquer um de seus nós. Faremos o mostrar na segunda porque ela é organizada pelo atributo Nome do Jogador. */

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

class No {
    public No esq;
    public No dir;
    public int chave;
    public Arvore arvore;

    public No(int chave) {
        this(chave, null, null);
    }

    public No(int chave, No esq, No dir) {
        this.chave = chave;
        this.esq = esq;
        this.dir = dir;
        this.arvore = new Arvore();
    }
}

class No2 {
    public No2 esq;
    public No2 dir;
    public String nome;

    No2() {
        this("");
    }

    No2(String nome) {
        this.nome = nome;
        this.esq = this.dir = null;
    }

}

class Arvore{
    No2 raiz;

    Arvore(){
        raiz = null;
    }
}

class ArvoreArvore{
    No raiz;
    int comparacoes;

    ArvoreArvore(){
        comparacoes = 0;
        inserir(7);
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(13);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(12);
        inserir(14);
    }

    public void inserir(int x){
        raiz = inserir(x,raiz);
    }

    private No inserir(int x, No i){
        comparacoes++;
        if(i == null){
            i = new No(x);
        }else if(x < i.chave){
            comparacoes++;
            i.esq = inserir(x,i.esq);
        }else if(x > i.chave){
            comparacoes++;
            i.dir = inserir(x,i.dir);
        }else{
            System.out.println("Erro ao inserir!");
        }
        return i;
    }

    public void inserir(Jogador x){
        raiz = inserir(x,raiz);
    }

    public No inserir(Jogador x, No i){
        if(i != null){
            if(x.getAltura()%15 < i.chave){
                comparacoes++;
                i.esq = inserir(x,i.esq);
            }else if(x.getAltura()%15 > i.chave){
                comparacoes++;
                i.dir = inserir(x,i.dir);
            }else{
                i.arvore.raiz = inserir2(x,i.arvore.raiz);
            }
        }
        return i;
    }

    No2 inserir2(Jogador x, No2 i){
        if(i == null){
            i = new No2(x.getNome());
        }else if(x.getNome().compareTo(i.nome) < 0){
            comparacoes++;
            i.esq = inserir2(x,i.esq);
        }else if(x.getNome().compareTo(i.nome) > 0){
            comparacoes++;
            i.dir = inserir2(x,i.dir);
        }else{
            System.out.println("Erro ao inserir!");
        }
        return i;
    }

    public boolean pesquisar(String nome){
        System.out.print(nome+" raiz");
        return pesquisar(nome,raiz);
    }

    public boolean pesquisar(String nome, No i){
        boolean resp = false;
        if(i!=null){
            if(!resp){
                resp = pesquisar2(nome, i.arvore.raiz);
            }
            if(!resp){
                System.out.print(" ESQ");
                comparacoes++;
                resp = pesquisar(nome,i.esq);
            }
            if(!resp){
                System.out.print(" DIR");
                comparacoes++;
                resp = pesquisar(nome,i.dir);
            }
        }
        return resp;
    }
    
    public boolean pesquisar2(String nome, No2 i){
        boolean resp = false;
        if(i!=null){
            if(nome.compareTo(i.nome) == 0){
                resp = true;
            }else{
                System.out.print(" ESQ");
                comparacoes++;
                resp = pesquisar2(nome,i.esq);
                if(!resp){
                    System.out.print(" DIR");
                    comparacoes++;
                    resp = pesquisar2(nome,i.dir);
                }
            }
        }
        return resp;
    }

    public int getComparacoes(){
        return comparacoes;
    }
}


class Q02{
    
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

        ArvoreArvore arvore = new ArvoreArvore();

        String entrada = MyIO.readLine();
        while(!entrada.equals("FIM")){
            arvore.inserir(jogadores[Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();
        while(!entrada.equals("FIM")){
            //System.out.print(entrada+" ");
            if(arvore.pesquisar(entrada)){
                System.out.println(" SIM");
            }else{
                System.out.println(" NAO");
            }
            entrada = MyIO.readLine();
        }

        long end = System.currentTimeMillis();

        Arq.openWrite("808674_arvoreArvore.txt");
        Arq.print("808674\t"+"\t"+arvore.getComparacoes()+"\t"+(end-start)/1000.0);
    }
}

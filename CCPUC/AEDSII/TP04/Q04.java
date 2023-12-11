
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

class NoAN{
    public boolean cor;
    public Jogador elemento;
    public NoAN esq, dir;

    public NoAN(){
        this(null);
    }

    public NoAN(Jogador elemento){
        this(elemento, false, null, null);
    }

    public NoAN(Jogador elemento, boolean cor){
        this(elemento, cor, null, null);
    }

    public NoAN(Jogador elemento, boolean cor, NoAN esq, NoAN dir){
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreAN{
    private NoAN raiz; // Raiz da arvore.
    private int comparacoes;

    /**
     * Construtor da classe.
     */
    public ArvoreAN(){
        raiz = null;
        comparacoes = 0;
    }

    public int getComparacoes(){
        return comparacoes;
    }

    public void setComparacoes(int comparacoes){
        this.comparacoes = comparacoes;
    }

    public void inserir(Jogador elemento){
        if(raiz == null){
            raiz = new NoAN(elemento, false);

        }else if(raiz.esq == null && raiz.dir == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new NoAN(elemento, true);
            }else{
                raiz.dir = new NoAN(elemento, true);
            }

        }else if(raiz.esq == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new NoAN(elemento);
            }else if(raiz.dir.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            }else{
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        }else if(raiz.dir == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) < 0){
                raiz.dir = new NoAN(elemento);
            }else if(raiz.esq.elemento.getNome().compareTo(elemento.getNome()) < 0){
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            }else{
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        }else{
            inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i){
        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){
            //4 tipos de reequilibrios e acoplamento
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0){
                    avo = rotacaoEsq(avo);
                }else{
                    avo = rotacaoDirEsq(avo);
                }

            }else{ // rotacao a direita ou esquerda-direita
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                    avo = rotacaoDir(avo);
                }else{
                    avo = rotacaoEsqDir(avo);
                }
            }

            if(bisavo == null){
                raiz = avo;
            }else{
                if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0){
                    bisavo.esq = avo;
                }else{
                    bisavo.dir = avo;
                }
            }

            //reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } 
    }

    private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i){
        if(i == null){
            if(elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                i = pai.esq = new NoAN(elemento, true);
            }else{
                i = pai.dir = new NoAN(elemento, true);
            }
            if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
            }
        }else{
            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz){
                    i.cor = false;
                }else if(pai.cor == true){
                    balancear(bisavo, avo, pai, i);
                }
            }
            if(elemento.getNome().compareTo(i.elemento.getNome()) < 0){
                inserir(elemento, avo, pai, i, i.esq);
            }else if(elemento.getNome().compareTo(i.elemento.getNome()) > 0){
                inserir(elemento, avo, pai, i, i.dir);
            }else{
                System.out.println("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no){
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no){
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no){
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no){
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    public boolean pesquisar(String x){
        System.out.print(x+" raiz");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, NoAN i){
        boolean resp;
        if(i == null){
            resp = false;
        }else if(x.compareTo(i.elemento.getNome()) == 0){
            resp = true;
        }else if(x.compareTo(i.elemento.getNome()) < 0){
            System.out.print(" esq");
            comparacoes++;
            resp = pesquisar(x, i.esq);
        }else{
            System.out.print(" dir");
            comparacoes++;
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

}
        




class Q04{
    
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

        ArvoreAN arvore = new ArvoreAN();

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            arvore.inserir(jogadores[Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            if(arvore.pesquisar(entrada)){
                System.out.println(" SIM");
            }else{
                System.out.println(" NAO");
            }
            entrada = MyIO.readLine();
        }

        long end = System.currentTimeMillis();

        Arq.openWrite("808674_alvinegra.txt");
        Arq.print("808674\t"+"\t"+arvore.getComparacoes()+"\t"+(end-start)/1000.0);
    }
}

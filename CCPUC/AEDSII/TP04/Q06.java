/*Crie duas árvores do tipo trie com os nomes dos Jogadores, fazendo inserções de registros conforme a entrada padrão. Não insira um elemento se sua chave estiver na árvore. Em seguida, faça o merge das duas árvores. Na árvore resultante (que não tem nomes repetidos), pesquise alguns nomes. A entrada padrão é igual a da questão de Pesquisa Sequencial. Entretanto, após a primeira ocorrência da palavra FIM, temos outros Jogadores que devem ser inseridos na segunda árvore.


A saída padrão é composta por várias linhas, uma para cada pesquisa resultando nas palavras SIM ou NÃO. Além disso, crie um arquivo de log na pasta corrente com o nome matrícula_arvoreTrie.txt com uma única linha contendo sua matrícula, tempo de execução do seu algoritmo e número de comparações. Todas as informações do arquivo de log devem ser separadas por uma tabulação '\t'.

 */
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
    public char elemento;
    public No[] prox;
    public boolean folha;
    public int tam;

    public No (){
        this(' ');
    }

    public No (char elemento){
        this.elemento = elemento;
        this.tam = 255;
        this.folha = false;
        prox = new No[tam];
        for(int i = 0; i < tam; i++){
            prox[i] = null;
        }
    }

    public static int hash (char x){
        return (int)x;
    }
}

class ArvoreTrie{
    public int comparacoes;
    private No raiz;
    public ArvoreTrie(){
        raiz = new No();
        comparacoes = 0;
    }

    public void inserir(String s){
        inserir(s, raiz, 0);
    }

    public void inserir(String s, No no, int i){
        if(no.prox[s.charAt(i)] == null){
            no.prox[s.charAt(i)] = new No(s.charAt(i));
            comparacoes++;
            if(i == s.length()-1){
                no.prox[s.charAt(i)].folha = true;
                comparacoes++;
            }else{
                inserir(s, no.prox[s.charAt(i)], i+1);
            }
        }else if(no.prox[s.charAt(i)].folha == false && i < s.length()-1){
            comparacoes++;
            inserir(s, no.prox[s.charAt(i)], i+1);
        }else{
            System.out.println("Erro ao inserir");
        }
    }

    public boolean pesquisar(String s){
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i){
        boolean resp = false;
        comparacoes++;
        if(no.prox[s.charAt(i)] == null){
            resp = false;
            comparacoes++;
        }else if(i == s.length()-1){
            resp = (no.prox[s.charAt(i)].folha == true);
            comparacoes++;
        }else if(i < s.length()-1){
            resp = pesquisar(s, no.prox[s.charAt(i)], i+1);
        }else{
            System.out.println("Erro ao pesquisar");
        }
        return resp;
    }

    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no){
        comparacoes++;
        if(no.folha == true){
            System.out.println(s+no.elemento);
        }else{
            for(int i = 0; i < no.prox.length; i++){
                comparacoes++;
                if(no.prox[i] != null){
                    mostrar(s+no.elemento, no.prox[i]);
                }
            }
        }
    }

    public void concatenar(ArvoreTrie arvore, ArvoreTrie arvore2){
        concatenar("", arvore, arvore2.raiz);
    }

    
    public void concatenar(String s, ArvoreTrie arvore, No no){
        comparacoes++;
        if(no.folha == true){
            arvore.inserir(s+no.elemento);
        }else{
            for(int i = 0; i < no.prox.length; i++){
                comparacoes++;
                if(no.prox[i] != null){
                    concatenar(s+no.elemento, arvore, no.prox[i]);
                }
            }
        }
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
        long start = System.currentTimeMillis();

        Jogador[] jogadores = new Jogador[3922];
        preencheArray(jogadores);

        ArvoreTrie arvore = new ArvoreTrie();
        ArvoreTrie arvore2 = new ArvoreTrie();

        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            arvore.inserir(jogadores[Integer.parseInt(entrada)].getNome());
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            arvore2.inserir(jogadores[Integer.parseInt(entrada)].getNome());
            entrada = MyIO.readLine();
        }

        arvore.concatenar(arvore, arvore2);

        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            System.out.print(entrada+" ");
            if(arvore.pesquisar(entrada)){
                System.out.println("SIM");
            }else{
                System.out.println("NAO");
            }
            entrada = MyIO.readLine();
        }

        long end = System.currentTimeMillis();

        Arq.openWrite("808674_arvoreTrie.txt");
        Arq.print("808674\t"+"\t"+arvore.comparacoes+"\t"+(end-start)/1000.0);
        Arq.close();
    }
}

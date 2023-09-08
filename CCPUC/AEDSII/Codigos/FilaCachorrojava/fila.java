class Cachorro {
    String nome;
    String raca;

    public Cachorro(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
    }

    public void printCachorro() {
        System.out.println(nome + ":  " + raca);
    }
}

class FilaCircular {
    Cachorro[] v;
    int p, u;

    public FilaCircular() {
        this(5);
    }

    public FilaCircular(int tam) {
        this.v = new Cachorro[tam + 1];
        p = u = 0;
    }

    public void inserir(String nome, String raca) throws Exception {
        if (((u + 1) % v.length) == p) {
            throw new Exception("Error");
        }
        v[u] = new Cachorro(nome, raca);
        u = (u + 1) % v.length;
    }

    public Cachorro remover() throws Exception {
        if (p == u) {
            throw new Exception("erro remover");
        }
        Cachorro resp = v[p];
        p = (p + 1) % v.length;
        return resp;
    }

    public void mostrar() {
        int i = p;
        while (i != u) {
            v[i].printCachorro(); // Fixed the method call
            i = (i + 1) % v.length;
        }
    }
}

public class fila {
    public static void main(String[] args) {
        try {
            FilaCircular fila = new FilaCircular();
            fila.inserir("snoopy", "shitzu");
            fila.inserir("minie", "shihtzu");
            fila.inserir("negao", "tudojunto");
            fila.inserir("tsunami", "labrador");
            fila.inserir("stella", "bulldog");
            fila.mostrar();
            Cachorro removido = fila.remover();
            fila.inserir("negao", "labrador");
            fila.mostrar();
            MyIO.println("Cachorro removido: ");
            removido.printCachorro();
        } catch (Exception e) {
            System.out.println("deu erro");
        }
    }
}

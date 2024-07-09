import java.util.*;
class No{
    No esq,dir;
    int elemento;

    public No(){
        this(null,null,0);
    }

    public No(No esq, No dir, int elemento){
        this.esq = esq;
        this.dir = dir;
        this.elemento = elemento;
    }
}

class ArvoreBinaria{
    No raiz;

    public ArvoreBinaria(){
        raiz = null;
    }

    public void inserir(int elemento){
        raiz = inserir(raiz,elemento);
    }

    private No inserir(No i, int elemento){
        if(i==null){
            i = new No(null,null,elemento);
        }else{
            if(elemento<i.elemento){
                i.esq = inserir(i.esq,elemento);
            }else if(elemento>i.elemento){
                i.dir = inserir(i.dir,elemento);
            }
        }
        return i;
    }

    public No mostrar(){
        return mostrar(raiz);
    }

    private No mostrar(No i){
        if(i!=null){
            System.out.print(i.elemento+" ");
            mostrar(i.esq);
            mostrar(i.dir);
        }
        return i;
    }
}




/**
 * This class implements the Breadth-First Search (BFS) algorithm for a binary tree.
 */
public class Bfs {
    /**
     * Performs the Breadth-First Search (BFS) algorithm on the given binary tree.
     * Prints the elements of the tree in breadth-first order.
     *
     * @param arvore The binary tree to perform BFS on.
     */
    public static void befs(ArvoreBinaria arvore){
        // Implementation of BFS algorithm
        int[] vet = new int[500];
        int i=0;
        Queue<No> Fila = new LinkedList<No>();
        Fila.add(arvore.raiz);

        while(!Fila.isEmpty()){
            No no = Fila.remove();
            vet[i] = no.elemento;
            i++;

            if(no.esq!=null){
                Fila.add(no.esq);
            }
            if(no.dir!=null){
                Fila.add(no.dir);
            }
        }

        // Print the elements in breadth-first order
        for(int j=0;j<i;j++){
            System.out.print(vet[j] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        for(int i=0;i<c;i++){
            int n = sc.nextInt();
            ArvoreBinaria arvore = new ArvoreBinaria();
            for(int j = 0 ; j < n ; j++){
                int numeros = sc.nextInt();
                arvore.inserir(numeros);
            }
            befs(arvore);
        }

    }
}

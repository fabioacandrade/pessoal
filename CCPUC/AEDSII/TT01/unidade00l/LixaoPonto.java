
/*Um aluno desenvolveu a classe abaixo e pediu sua ajuda para compilá-la. Para ajudar, você deve criar uma classe Ponto com as seguintes regras:
• Quatro atributos privados: double x, double y, int id e int nextID
• Os atributos id e nextID serão alterados somente por um método construtor.
• Implemente os métodos get e set tanto para o atributo x como para o y
• Na declaração do atributo nextID, o mesmo deve receber zero. Além disso, a alteração do valor desse
atributo por um objeto sempre será compartilhada com qualquer objeto da classe ponto
• Implemente dois construtores sendo que o primeiro não recebe parâmetros e inicializa os valores de x e
y com zero. O segundo recebe dois parâmetros (cujos nomes são obrigatoriamente x e y) e devem ser
utilizados para inicializar os valores dos atributos x e y, respectivamente
• Os dois construtores devem atribuir o valor corrente do atributo nextID ao atributo id e incrementar o
valor de nextID. Observe que cada objeto terá um ID distinto
• Implemente qualquer método que seja necessário para compilar a classe LixaoPonto */
import java.util.Scanner;

public class LixaoPonto {
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;

    public LixaoPonto() {
        this.x = 0;
        this.y = 0;
        this.id = nextID;
        nextID++;
    }

    public LixaoPonto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = nextID;
        nextID++;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getId() {
        return this.id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static void main(String[] args) {
        Ponto p1 = new Ponto(4, 3);
        Ponto p2 = new Ponto(8, 5);
        Ponto p3 = new Ponto(9.2, 10);
        System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
        System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5, 2));
        System.out.println("Distancia (4,3) entre e (5,2): " + Ponto.dist(4, 3, 5, 2));
        System.out.println("P1, P2, P3 sao triangulo:" + Ponto.isTriangulo(p1, p2, p3));
        System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
        System.out.println("ID de P1: " + p1.getID());
        System.out.println("ID de P2: " + p2.getID());
        System.out.println("ID de P3: " + p3.getID());
        System.out.println("Next ID: " + Ponto.getNextID());
    }

}

import java.io.*;

class Principal {

  public static void main(String args[]) {

    Arquivo arqLivros;
    Livro l1 = new Livro(-1, "Odisséia", "Homero", 15.99F);
    Livro l2 = new Livro(-1, "Ensino Híbrido", "Lilian Bacich", 39.90F);
    Livro l3 = new Livro();
    int id1, id2;

    try {
      arqLivros = new Arquivo("dados/livros.db");

      id1 = arqLivros.create(l1);
      System.out.println("Livro criado com o ID: " + id1);

      id2 = arqLivros.create(l2);
      System.out.println("Livro criado com o ID: " + id2);

      l3 = arqLivros.read(id1);
      System.out.println(l3);

      l3 = arqLivros.read(id2);
      System.out.println(l3);

      arqLivros.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.NumberFormat;

public class Livro {

  private int ID;
  private String titulo;
  private String autor;
  private float preco;

  public Livro() {
    this(-1, "", "", 0F);
  }

  public Livro(String t, String a, float p) {
    this(-1, t, a, p);
  }

  public Livro(int id, String t, String a, float p) {
    this.ID = id;
    this.titulo = t;
    this.autor = a;
    this.preco = p;
  }

  public int getID() {
    return this.ID;
  }

  public void setID(int i) {
    this.ID = i;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream ba_out = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(ba_out);
    dos.writeInt(this.ID);
    dos.writeUTF(this.titulo);
    dos.writeUTF(this.autor);
    dos.writeFloat(this.preco);
    return ba_out.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws Exception {
    ByteArrayInputStream ba_in = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(ba_in);
    this.ID = dis.readInt();
    this.titulo = dis.readUTF();
    this.autor = dis.readUTF();
    this.preco = dis.readFloat();
  }

  public String toString() {
    return "ID: " + this.ID +
        "\nTítulo: " + this.titulo +
        "\nAutor: " + this.autor +
        "\nPreço: " + NumberFormat.getCurrencyInstance().format(this.preco);
  }

}

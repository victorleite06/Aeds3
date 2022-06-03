import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Livro implements Registro {

  int ID;
  String titulo;
  String autor;
  float preco;

  public Livro() {
    this(-1, "", "", 0F);
  }

  public Livro(String t, String a, float p) {
    this(-1, t, a, p);
  }

  public Livro(int i, String t, String a, float p) {
    this.ID = i;
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

  public String getTitulo(){
    return this.titulo;
  }

  public void setTitulo(String titulo){
    this.titulo = titulo;
  }

  public String getAutor(){
    return this.autor;
  }

  public void setAutor(String autor){
    this.autor = autor;
  }

  public float getPreco(){
    return this.preco;
  }

  public void setPreco(float preco){
    this.preco = preco;
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
    return "\nID: " + this.ID +
        "\nTítulo: " + this.titulo +
        "\nAutor: " + this.autor +
        "\nPreço: R$ " + this.preco;
  }

}
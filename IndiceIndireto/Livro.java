import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;

public class Livro implements Registro { // Classe == TIPO DE ENTIDADE

  private int ID;
  private String titulo;
  private String autor;
  private float preco;
  private Date lancamento;
  private String isbn;

  public Livro() {
    this(-1, "", "", 0F, new Date(), "");
  }

  public Livro(String t, String a, float p, String is){
    this(-1,t,a,p, new Date(), is);
  }
  
  public Livro(String is, String t, String a, float p, Date d) {
    this(-1, t, a, p, d, is);
  }

  public Livro(int i, String t, String a, float p, Date d, String is) {
    this.ID = i;
    this.titulo = t;
    this.autor = a;
    this.preco = p;
    this.lancamento = d;
    this.isbn = is;
  }

  public int getID() {
    return this.ID;
  }

  public void setID(int i) {
    this.ID = i;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String t) {
    this.titulo = t;
  }

  public String getAutor() {
    return this.autor;
  }

  public void setAutor(String a) {
    this.autor = a;
  }

  public float getPreco() {
    return this.preco;
  }

  public void setPreco(float p) {
    this.preco = p;
  }

  public Date getLancamento() {
    return this.lancamento;
  }

  public void setLancamento(long d) {
    this.lancamento = new Date(d);
  }

  public String getISBN(){
    return this.isbn;
  }

  public void setISBN(String isbn){
    this.isbn = isbn;
  }

  public String toString() {
    return "\nID: " + this.ID +
        "\nISBN: " + this.isbn +
        "\nTítulo: " + this.titulo +
        "\nAutor: " + this.autor +
        "\nPreço: R$ " + this.preco +
        "\nData de lançamento: " + this.lancamento;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.ID);
    dos.writeUTF(this.isbn);
    dos.writeUTF(this.titulo);
    dos.writeUTF(this.autor);
    dos.writeFloat(this.preco);
    dos.writeLong(this.lancamento.getTime());
    byte[] dados = baos.toByteArray();
    return dados;
  }

  public void fromByteArray(byte[] vb) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(vb);
    DataInputStream dis = new DataInputStream(bais);
    this.ID = dis.readInt();
    this.isbn = dis.readUTF();
    this.titulo = dis.readUTF();
    this.autor = dis.readUTF();
    this.preco = dis.readFloat();
    this.lancamento = new Date(dis.readLong());
  }
}

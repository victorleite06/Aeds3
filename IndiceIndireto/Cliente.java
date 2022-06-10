import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Cliente implements Registro {

  private int ID;
  private String nome;
  private String email;
  private String CPF;

  public Cliente() {
    this(-1, "", "","");
  }

  public Cliente(String n, String e, String c) {
    this(-1, n, e,c);
  }

  public Cliente(int i, String n, String e,String c) {
    this.ID = i;
    this.nome = n;
    this.email = e;
    this.CPF = c;
  }

  public String getCPF(){
    return this.CPF;
  }

  public int getID() {
    return this.ID;
  }

  public void setID(int i) {
    this.ID = i;
  }

    public void setCPF(String s) {
    this.CPF = s;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String s) {
    this.nome = s;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String s) {
    this.email = s;
  }

  public String toString() {
    return "\nID: " + this.ID +
        "\nNome: " + this.nome +
        "\nEmail: " + this.email+
        "\nCPF: " + this.CPF;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.ID);
    dos.writeUTF(this.nome);
    dos.writeUTF(this.email);
    dos.writeUTF(this.CPF);
    byte[] dados = baos.toByteArray();
    return dados;
  }

  public void fromByteArray(byte[] vb) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(vb);
    DataInputStream dis = new DataInputStream(bais);
    this.ID = dis.readInt();
    this.nome = dis.readUTF();
    this.email = dis.readUTF();
    this.CPF = dis.readUTF();
  }

}
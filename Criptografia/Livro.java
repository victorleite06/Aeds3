import java.util.Random;
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
  private String chave = "alunos";
  private byte[] chaveByt;

  public Livro() {
    this(-1, "", "", 0F, new Date(), "");
  }

  public Livro(String t, String a, float p, String is){
    this(-1,t,a,p, new Date(), is);
  }

  public Livro(String is, String t, String a, float p, Date d) {
    this(-1, t, a, p, d, is);
  }
  
  public Livro(String t, String a, float p, Date d, String is) {
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
    byte[] dadosCifrados = cifrar(dados, chave);
    return dadosCifrados;
  }

  public void fromByteArray(byte[] vb) throws Exception {
    byte[] dadosDecifrados = decifrar(vb, chave);
    ByteArrayInputStream bais = new ByteArrayInputStream(dadosDecifrados);
    DataInputStream dis = new DataInputStream(bais);
    this.ID = dis.readInt();
    this.isbn = dis.readUTF();
    this.titulo = dis.readUTF();
    this.autor = dis.readUTF();
    this.preco = dis.readFloat();
    this.lancamento = new Date(dis.readLong());
  }

  private byte[] cifrar(byte[] dados, String chave){
    byte[] cesar = new byte[dados.length]; // Ciframento de Cesar
    for(int i = 0;i < dados.length;i++){
       cesar[i] = (byte)(dados[i] + (chave.length() % 26));
    }

    byte[] cifr = new byte[cesar.length]; // Ciframento que inverte as letras
    int base = (((int) 'z') - ((int) 'a')) / 2;
    for(int i = 0;i < cesar.length;i++){
      if(( (int) (cifr[i]) ) < base){
        cifr[i] = (byte) (( (int) ('z') ) - cifr[i]);
      }else{
        cifr[i] = (byte) (( (int) ('a') ) + cifr[i]);
      }
    }    
    
    byte[] cifrado = new byte[cifr.length]; // Ciframento de Fluxo
    this.chaveByt = new byte[dados.length];
    Random rnd = new Random();
    rnd.nextBytes(chaveByt);
    for(int i = 0;i < dados.length;i++){
      cifrado[i] = (byte) (dados[i] ^ chaveByt[i]);
    }
    
    return cifrado;
  }

  private byte[] decifrar(byte[] dados, String chave){
    byte[] fluxo = new byte[dados.length]; // Ciframento de Fluxo
    for(int i = 0;i < dados.length;i++){
      fluxo[i] = (byte) (dados[i] ^ this.chaveByt[i]);
    }
    
    byte[] cifr = new byte[fluxo.length]; // Ciframento que inverte as letras
    int base = (((int) 'z') - ((int) 'a')) / 2;
    for(int i = 0;i < fluxo.length;i++){
      if(( (int) (cifr[i]) ) < base){
        cifr[i] = (byte) (( (int) ('a') ) + cifr[i]);
      }else{
        cifr[i] = (byte) (( (int) ('z') ) - cifr[i]);
      }
    }
    
    byte[] decifrado = new byte[cifr.length]; // Ciframento de Cesar
    for(int i = 0;i < cifr.length;i++){
       decifrado[i] = (byte)(cifr[i] - (chave.length() % 26));
    }
    
    return decifrado;
  }

}
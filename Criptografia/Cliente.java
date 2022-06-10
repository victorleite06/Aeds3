import java.util.Random;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Cliente implements Registro {

  private int ID;
  private String nome;
  private String email;
  private String CPF;
  private byte[] chaveByt;
  
  private String chave = "alunos";

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
    byte[] dadosCifrados = cifrar(dados, chave);
    return dadosCifrados;
  }

  public void fromByteArray(byte[] vb) throws Exception {
    byte[] dadosDecifrados = decifrar(vb, chave);
    ByteArrayInputStream bais = new ByteArrayInputStream(dadosDecifrados);
    DataInputStream dis = new DataInputStream(bais);
    this.ID = dis.readInt();
    this.nome = dis.readUTF();
    this.email = dis.readUTF();
    this.CPF = dis.readUTF();
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
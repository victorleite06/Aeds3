public interface Registro {
  public int getID();

  public void setID(int id);

  public byte[] toByteArray() throws Exception;

  public void fromByteArray(byte[] ba) throws Exception;
}
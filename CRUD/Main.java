import java.io.File;
import java.util.Scanner;

class Main {

  private static Scanner console = new Scanner(System.in);

  public static void main(String args[]) {

    File d;
    d = new File("dados");
    if (!d.exists())
      d.mkdir();
    d = new File("dados/clientes");
    if (!d.exists())
      d.mkdir();
    d = new File("dados/livros");
    if (!d.exists())
      d.mkdir();

    try {
      // Menu principal
      int opcao;
      do {
        System.out.println("\n\nMenu principal");
        System.out.println("--------------");
        System.out.println("1) Livros");
        System.out.println("2) Clientes");
        System.out.println("0) Sair");
        System.out.print("\nOpção: ");

        try {
          opcao = Integer.valueOf(console.nextLine());
        } catch (NumberFormatException e) {
          opcao = -1;
        }

        switch (opcao) {
          case 1:
            (new InteracaoLivros()).MenuLivros();
            break;
          case 2:
            (new InteracaoClientes()).MenuClientes();
            break;
          case 0:
            break;
          default:
            System.out.println("Opção inválida!");
        }
      } while (opcao != 0);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
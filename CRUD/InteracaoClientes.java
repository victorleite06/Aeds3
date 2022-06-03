import java.util.Scanner;

public class InteracaoClientes {

  private static Scanner console = new Scanner(System.in);
  private Arquivo<Cliente> arqCliente;

  public void MenuClientes() {

    try {
      arqCliente = new Arquivo<>(Cliente.class.getConstructor(), "dados/clientes/dados.db");

      // Menu principal
      int opcao;
      do {
        System.out.println("\n\nMenu de Clientes");
        System.out.println("--------------");
        System.out.println("1) Buscar");
        System.out.println("2) Incluir");
        System.out.println("3) Alterar");
        System.out.println("4) Excluir");
        System.out.println("0) Voltar");
        System.out.print("\nOpção: ");

        try {
          opcao = Integer.valueOf(console.nextLine());
        } catch (NumberFormatException e) {
          opcao = -1;
        }

        switch (opcao) {
          case 1:
            ConsultarCliente();
            break;
          case 2:
            IncluirCliente();
            break;
          case 3:
            AlterarCliente();
            break;
          case 4:
            ExcluirCliente();
            break;
          case 0:
            break;
          default:
            System.out.println("Opção inválida!");
        }
      } while (opcao != 0);

      arqCliente.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void ConsultarCliente() {
    int id;
    System.out.print("\nID do Cliente: ");
    try {
      id = Integer.valueOf(console.nextLine());
      Cliente l = arqCliente.read(id);
      System.out.println(l);
    } catch (NumberFormatException e) {
      System.out.println("ID inválido!");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void IncluirCliente()throws Exception{
    String nom;
    String email;
    System.out.print("\nNome do Cliente: ");
    nom = console.nextLine();
    System.out.print("\nEmail do Cliente: ");
    email = console.nextLine();
    Cliente c = new Cliente(nom, email);
    int id = arqCliente.create(c);
    c.setID(id);
  }

  public void AlterarCliente()throws Exception{
    System.out.println("\nId do Cliente:");
    int id;
    Cliente l = new Cliente();
    try {
      id = Integer.valueOf(console.nextLine());
      l = arqCliente.read(id);
    } catch (NumberFormatException e) {
      System.out.println("ID inválido!");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("\n\nMenu de Alteracao");
    System.out.println("--------------");
    System.out.println("1) Nome");
    System.out.println("2) Email");
    System.out.println("0) Sair");
    System.out.print("\nOpção: ");
    int op = Integer.parseInt(console.nextLine());
    switch(op){
      case 1:
        String nome = console.nextLine();
        l.setNome(nome);
        if(arqCliente.update(l)){
          System.out.println("Atualizado!");
        }else{
          System.out.println("Nao atualizado!");
        }
        break;

      case 2:
        String email = console.nextLine();
        l.setEmail(email);
        if(arqCliente.update(l)){
          System.out.println("Atualizado!");
        }else{
          System.out.println("Nao atualizado!");
        }
        break;

      case 0:
        break;

      default:
        System.out.println("Opcao invalida!!!");
    }
  }

  public void ExcluirCliente()throws Exception{
    System.out.println("\nId do Cliente:");
    int id = Integer.parseInt(console.nextLine());
    if(arqCliente.delete(id)){
      System.out.println("Cliente deletado!");
    }else{
      System.out.println("Clinte nao deletado!!!");
    }
  }

}
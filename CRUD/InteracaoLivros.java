import java.util.Scanner;

public class InteracaoLivros {

  private static Scanner console = new Scanner(System.in);
  private Arquivo<Livro> arqLivros;

  public void MenuLivros() {

    try {
      arqLivros = new Arquivo<>(Livro.class.getConstructor(), "dados/livros/dados.db");

      // Menu principal
      int opcao;
      do {
        System.out.println("\n\nMenu de Livros");
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
            ConsultarLivro();
            break;
          case 2:
            IncluirLivro();
            break;
          case 3:
            AlterarLivro();
            break;
          case 4:
            ExcluirLivro();
            break;
          case 0:
            break;
          default:
            System.out.println("Opção inválida!");
        }
      } while (opcao != 0);

      arqLivros.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void ConsultarLivro() {
    int id;
    System.out.print("\nID do Livro: ");
    try {
      id = Integer.valueOf(console.nextLine());
      Livro l = arqLivros.read(id);
      System.out.println(l);
    } catch (NumberFormatException e) {
      System.out.println("ID inválido!");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void IncluirLivro()throws Exception{
    String nom;
    String auto;
    float prec;
    System.out.print("\nNome do Livro: ");
    nom = console.nextLine();
    System.out.print("\nAutor do Livro: ");
    auto = console.nextLine();
    System.out.print("\nPreco do Livro: ");
    prec = Float.parseFloat(console.nextLine());
    Livro c = new Livro(nom, auto, prec);
    int id = arqLivros.create(c);
    c.setID(id);
  }

  public void AlterarLivro()throws Exception{
    System.out.println("\nId do Cliente:");
    int id;
    Livro l = new Livro();
    try {
      id = Integer.valueOf(console.nextLine());
      l = arqLivros.read(id);
    } catch (NumberFormatException e) {
      System.out.println("ID inválido!");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("\n\nMenu de Alteracao");
    System.out.println("--------------");
    System.out.println("1) Titulo");
    System.out.println("2) Autor");
    System.out.println("3) Preco");
    System.out.println("0) Voltar");
    System.out.print("\nOpção: ");
    int op = Integer.parseInt(console.nextLine());
    switch(op){
      case 1:
        String titulo = console.nextLine();
        l.setTitulo(titulo);
        if(arqLivros.update(l)){
          System.out.println("Atualizado!");
        }else{
          System.out.println("Nao atualizado!");
        }
        break;

      case 2:
        String autor = console.nextLine();
        l.setAutor(autor);
        if(arqLivros.update(l)){
          System.out.println("Atualizado!");
        }else{
          System.out.println("Nao atualizado!");
        }
        break;

      case 3:
        float preco = Float.parseFloat(console.nextLine());
        l.setPreco(preco);
        if(arqLivros.update(l)){
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

  public void ExcluirLivro()throws Exception{
    System.out.println("\nId do Cliente:");
    int id = Integer.parseInt(console.nextLine());
    if(arqLivros.delete(id)){
      System.out.println("Cliente deletado!");
    }else{
      System.out.println("Clinte nao deletado!!!");
    }
  }
  
}
package vendas.view;

import java.util.Scanner;
import vendas.controller.ProdutoController;
import vendas.model.Produto;

public class ProdutoView {

    Scanner sc = new Scanner(System.in);
    ProdutoController controller = new ProdutoController();

    public void menu() {

        int opcao;

        do {
            System.out.println("\n--- MENU PRODUTO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    cadastrar();
                    break;

                case 2:
                    alterar();
                    break;

                case 3:
                    excluir();
                    break;

                case 4:
                    pesquisar();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrar() {

        Produto p = new Produto();

        System.out.print("Nome: ");
        p.setNome(sc.nextLine());

        System.out.print("Preço: ");
        p.setPreco(sc.nextDouble());
        sc.nextLine();

        System.out.print("qtde_estoque em estoque: ");
        p.setQtde_estoque(sc.nextInt());
        sc.nextLine();

        boolean sucesso = controller.salvar(p);

        if (sucesso) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto!");
        }
    }

    private void alterar() {

        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Produto p = controller.pesquisar(id);

        if (p != null) {

            System.out.println("\n--- ALTERANDO PRODUTO ---");

            System.out.print("Nome (" + p.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) p.setNome(nome);

            System.out.print("Preço (" + p.getPreco() + "): ");
            double preco = sc.nextDouble();
            sc.nextLine();
            if (preco >= 0) p.setPreco(preco);

            System.out.print("qtde_estoque (" + p.getQtde_estoque() + "): ");
            int qtd = sc.nextInt();
            sc.nextLine();
            if (qtd >= 0) p.setQtde_estoque(qtd);

            boolean sucesso = controller.alterar(p);

            if (sucesso) {
                System.out.println("Produto alterado com sucesso!");
            } else {
                System.out.println("Erro ao alterar produto!");
            }

        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private void excluir() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = controller.excluir(id);

        if (sucesso) {
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir produto!");
        }
    }

    private void pesquisar() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Produto p = controller.pesquisar(id);

        if (p != null) {
            System.out.println("\n--- DADOS DO PRODUTO ---");
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Preço: " + p.getPreco());
            System.out.println("qtde_estoque em estoque: " + p.getQtde_estoque());
        } else {
            System.out.println("Produto não encontrado!");
        }
    }
}
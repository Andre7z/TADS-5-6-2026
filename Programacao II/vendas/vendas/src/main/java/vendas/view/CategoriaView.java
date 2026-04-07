package vendas.view;

import java.util.Scanner;

import vendas.controller.CategoriaController;
import vendas.model.Categoria;

public class CategoriaView {
    
    Scanner sc = new Scanner(System.in);
    CategoriaController controller = new CategoriaController();

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Categoria ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

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

        Categoria c = new Categoria();

        System.out.print("Nome: ");
        c.setNome(sc.nextLine());

        boolean sucesso = controller.salvar(c);

        if (sucesso) {
            System.out.println("Categoria cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar Categoria!");
        }
    }

    private void alterar() {

        System.out.print("ID do Categoria: ");
        int id = sc.nextInt();
        sc.nextLine();

        Categoria c = controller.pesquisar(id);

        if (c != null) {

            System.out.println("\n--- ALTERANDO Categoria ---");

            System.out.print("Nome (" + c.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty())
                c.setNome(nome);

            boolean sucesso = controller.alterar(c);

            if (sucesso) {
                System.out.println("Categoria alterado com sucesso!");
            } else {
                System.out.println("Erro ao alterar Categoria!");
            }

        } else {
            System.out.println("Categoria não encontrado!");
        }
    }

    private void excluir() {
        System.out.print("ID:");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = controller.excluir(id);

        if (sucesso) {
            System.out.println("Categoria excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir Categoria!");
        }
    }

    private void pesquisar() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Categoria c = controller.pesquisar(id);

        if (c != null) {
            System.out.println("\n--- DADOS DO Categoria ---");
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
    } else {
            System.out.println("Cliente não encontrado!");
        }
    }
}

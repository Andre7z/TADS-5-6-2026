package vendas.view;

import java.util.Scanner;
import vendas.controller.FornecedorController;
import vendas.model.Fornecedor;

public class FornecedorView {

    Scanner sc = new Scanner(System.in);
    FornecedorController controller = new FornecedorController();

    public void menu() {

        int opcao;

        do {
            System.out.println("\n--- MENU FORNECEDOR ---");
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

        Fornecedor f = new Fornecedor();

        System.out.print("Nome Fantasia: ");
        f.setNome_fantasia(sc.nextLine());

        System.out.print("Razão Social: ");
        f.setRazao_social(sc.nextLine());

        System.out.print("CNPJ: ");
        f.setCnpj(sc.nextLine());

        boolean sucesso = controller.salvar(f);

        if (sucesso) {
            System.out.println("Fornecedor cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar fornecedor!");
        }
    }

    private void alterar() {

        System.out.print("ID do fornecedor: ");
        int id = sc.nextInt();
        sc.nextLine();

        Fornecedor f = controller.pesquisar(id);

        if (f != null) {

            System.out.println("\n--- ALTERANDO FORNECEDOR ---");

            System.out.print("Nome Fantasia (" + f.getNome_fantasia() + "): ");
            String nomeFantasia = sc.nextLine();
            if (!nomeFantasia.isEmpty()) f.setNome_fantasia(nomeFantasia);

            System.out.print("Razão Social (" + f.getRazao_social() + "): ");
            String razao = sc.nextLine();
            if (!razao.isEmpty()) f.setRazao_social(razao);

            System.out.print("CNPJ (" + f.getCnpj() + "): ");
            String cnpj = sc.nextLine();
            if (!cnpj.isEmpty()) f.setCnpj(cnpj);

            boolean sucesso = controller.alterar(f);

            if (sucesso) {
                System.out.println("Fornecedor alterado com sucesso!");
            } else {
                System.out.println("Erro ao alterar fornecedor!");
            }

        } else {
            System.out.println("Fornecedor não encontrado!");
        }
    }

    private void excluir() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = controller.excluir(id);

        if (sucesso) {
            System.out.println("Fornecedor excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir fornecedor!");
        }
    }

    private void pesquisar() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Fornecedor f = controller.pesquisar(id);

        if (f != null) {
            System.out.println("\n--- DADOS DO FORNECEDOR ---");
            System.out.println("ID: " + f.getId());
            System.out.println("Nome Fantasia: " + f.getNome_fantasia());
            System.out.println("Razão Social: " + f.getRazao_social());
            System.out.println("CNPJ: " + f.getCnpj());
        } else {
            System.out.println("Fornecedor não encontrado!");
        }
    }
}
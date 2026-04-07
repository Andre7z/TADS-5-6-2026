package vendas.view;

import java.util.Scanner;

import vendas.controller.ClienteController;
import vendas.model.Cliente;

public class ClienteView {

    Scanner sc = new Scanner(System.in);
    ClienteController controller = new ClienteController();

    public void menu() {

        int opcao;

        do {
            System.out.println("\n--- MENU CLIENTE ---");
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

        Cliente c = new Cliente();

        System.out.print("Nome: ");
        c.setNome(sc.nextLine());

        System.out.print("CPF: ");
        c.setCpf(sc.nextLine());

        System.out.print("RG: ");
        c.setRg(sc.nextLine());

        System.out.print("Endereço: ");
        c.setEndereco(sc.nextLine());

        System.out.print("Telefone: ");
        c.setTelefone(sc.nextLine());

        boolean sucesso = controller.salvar(c);

        if (sucesso) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar cliente!");
        }
    }

    private void alterar() {

        System.out.print("ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente c = controller.pesquisar(id);

        if (c != null) {

            System.out.println("\n--- ALTERANDO CLIENTE ---");

            System.out.print("Nome (" + c.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) c.setNome(nome);

            System.out.print("CPF (" + c.getCpf() + "): ");
            String cpf = sc.nextLine();
            if (!cpf.isEmpty()) c.setCpf(cpf);

            System.out.print("RG (" + c.getRg() + "): ");
            String rg = sc.nextLine();
            if (!rg.isEmpty()) c.setRg(rg);

            System.out.print("Endereço (" + c.getEndereco() + "): ");
            String endereco = sc.nextLine();
            if (!endereco.isEmpty()) c.setEndereco(endereco);

            System.out.print("Telefone (" + c.getTelefone() + "): ");
            String telefone = sc.nextLine();
            if (!telefone.isEmpty()) c.setTelefone(telefone);

            boolean sucesso = controller.alterar(c);

            if (sucesso) {
                System.out.println("Cliente alterado com sucesso!");
            } else {
                System.out.println("Erro ao alterar cliente!");
            }

        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void excluir() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = controller.excluir(id);

        if (sucesso) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir cliente!");
        }
    }
    
    private void pesquisar() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente c = controller.pesquisar(id);

        if (c != null) {
            System.out.println("\n--- DADOS DO CLIENTE ---");
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("RG: " + c.getRg());
            System.out.println("Endereço: " + c.getEndereco());
            System.out.println("Telefone: " + c.getTelefone());
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }
}
package vendas.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import vendas.controller.VendaController;
import vendas.controller.ClienteController;
import vendas.controller.ProdutoController;
import vendas.model.Venda;
import vendas.model.Cliente;
import vendas.model.Produto;
import vendas.model.ItemVenda;

public class VendaView {

    Scanner sc = new Scanner(System.in);
    VendaController controller = new VendaController();
    ClienteController clienteController = new ClienteController();
    ProdutoController produtoController = new ProdutoController();

    public void menu() {

        int opcao;

        do {
            System.out.println("\n--- MENU VENDA ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: alterar(); break;
                case 3: excluir(); break;
                case 4: pesquisar(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrar() {

        Venda v = new Venda();

        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente c = clienteController.pesquisar(idCliente);
        if (c == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        v.setCliente(c);
        v.setData_venda(LocalDate.now());

        v.setItens(new ArrayList<>());

        boolean adicionarMais = true;

        while (adicionarMais) {

            System.out.print("ID do Produto: ");
            int idProduto = sc.nextInt();
            sc.nextLine();

            Produto p = produtoController.pesquisar(idProduto);
            if (p == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();
            sc.nextLine();

            ItemVenda item = new ItemVenda();
            item.setProduto(p);
            item.setQuantidade(qtd);
            item.setPreco_unit(p.getPreco());

            v.getItens().add(item);

            System.out.print("Adicionar outro produto? (s/n): ");
            String resp = sc.nextLine();
            adicionarMais = resp.equalsIgnoreCase("s");
        }

        // 🔥 CHAMADA CORRETA (COM REGRAS)
        boolean sucesso = controller.realizarVenda(v, v.getItens());

        if (sucesso) {
            System.out.println("Venda cadastrada com sucesso!");
        } else {
            System.out.println("Venda não realizada!");
        }
    }

    private void alterar() {

        System.out.print("ID da Venda: ");
        int id = sc.nextInt();
        sc.nextLine();

        Venda v = controller.pesquisar(id);
        if (v == null) {
            System.out.println("Venda não encontrada!");
            return;
        }

        System.out.println("\n--- ALTERANDO VENDA ---");

        System.out.print("ID do Cliente (" + v.getCliente().getId() + "): ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente c = clienteController.pesquisar(idCliente);
        if (c != null) v.setCliente(c);

        v.setItens(new ArrayList<>());

        boolean adicionarMais = true;

        while (adicionarMais) {

            System.out.print("ID do Produto: ");
            int idProduto = sc.nextInt();
            sc.nextLine();

            Produto p = produtoController.pesquisar(idProduto);
            if (p == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();
            sc.nextLine();

            ItemVenda item = new ItemVenda();
            item.setProduto(p);
            item.setQuantidade(qtd);
            item.setPreco_unit(p.getPreco());

            v.getItens().add(item);

            System.out.print("Adicionar outro produto? (s/n): ");
            String resp = sc.nextLine();
            adicionarMais = resp.equalsIgnoreCase("s");
        }

        double total = 0;
        for (ItemVenda item : v.getItens()) {
            total += item.getQuantidade() * item.getPreco_unit();
        }
        v.setValor_total(total);

        boolean sucesso = controller.alterar(v);

        if (sucesso) {
            System.out.println("Venda alterada com sucesso!");
        } else {
            System.out.println("Erro ao alterar venda!");
        }
    }

    private void excluir() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = controller.excluir(id);

        if (sucesso) {
            System.out.println("Venda excluída com sucesso!");
        } else {
            System.out.println("Erro ao excluir venda!");
        }
    }

    private void pesquisar() {

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Venda v = controller.pesquisar(id);

        if (v == null) {
            System.out.println("Venda não encontrada!");
            return;
        }

        System.out.println("\n--- DADOS DA VENDA ---");
        System.out.println("ID: " + v.getId());
        System.out.println("Cliente: " + v.getCliente().getNome());
        System.out.println("Data: " + v.getData_venda());

        System.out.println("Itens:");
        for (ItemVenda item : v.getItens()) {
            System.out.println("Produto: " + item.getProduto().getNome() +
                    " | Qtd: " + item.getQuantidade() +
                    " | Preço: " + item.getPreco_unit() +
                    " | Subtotal: " + (item.getQuantidade() * item.getPreco_unit()));
        }

        System.out.println("Total: " + v.getValor_total());
    }
}
package vendas.view;

import java.util.Scanner;

import vendas.controller.ProdutoController;
import vendas.model.Produto;
import vendas.model.Categoria;
import vendas.model.Fornecedor;
import vendas.model.FornecedorProduto;
import vendas.dao.FornecedorProdutoDAO;

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

        Produto p = new Produto();

        System.out.print("Nome: ");
        p.setNome(sc.nextLine());

        System.out.print("Preço: ");
        p.setPreco(sc.nextDouble());
        sc.nextLine();

        System.out.print("qtde_estoque em estoque: ");
        p.setQtde_estoque(sc.nextDouble());
        sc.nextLine();

        System.out.print("ID da Categoria: ");
        int idCat = sc.nextInt();
        sc.nextLine();

        Categoria cat = new Categoria();
        cat.setId(idCat);
        p.setCategoria(cat);

        boolean sucesso = controller.salvar(p);

        if (sucesso) {

            System.out.print("ID do fornecedor: ");
            int idForn = sc.nextInt();
            sc.nextLine();

            Fornecedor f = new Fornecedor();
            f.setId(idForn);

            FornecedorProduto fp = new FornecedorProduto();
            fp.setFornecedor(f);
            fp.setProduto(p);

            FornecedorProdutoDAO fpDAO = new FornecedorProdutoDAO();
            fpDAO.salvar(fp);

            System.out.println("Produto cadastrado com fornecedor!");
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

            System.out.print("Nome (" + p.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) p.setNome(nome);

            System.out.print("Preço (" + p.getPreco() + "): ");
            double preco = sc.nextDouble();
            sc.nextLine();
            if (preco >= 0) p.setPreco(preco);

            System.out.print("qtde_estoque (" + p.getQtde_estoque() + "): ");
            double qtd = sc.nextDouble();
            sc.nextLine();
            if (qtd >= 0) p.setQtde_estoque(qtd);

            System.out.print("ID Categoria: ");
            int idCat = sc.nextInt();
            sc.nextLine();

            Categoria cat = new Categoria();
            cat.setId(idCat);
            p.setCategoria(cat);

            controller.alterar(p);

            System.out.println("Produto alterado!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private void excluir() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        controller.excluir(id);
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
        System.out.println("Estoque: " + p.getQtde_estoque());
        System.out.println("Categoria: " + p.getCategoria().getId());

        if (p.getFornecedores() != null && !p.getFornecedores().isEmpty()) {

            System.out.println("Fornecedores:");

            for (Fornecedor f : p.getFornecedores()) {
                System.out.println("ID: " + f.getId() + " - " + f.getNome_fantasia());
            }

        } else {
            System.out.println("Sem fornecedores cadastrados.");
        }

    } else {
        System.out.println("Produto não encontrado!");
    }
}
}
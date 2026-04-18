package avaliacao1.controller;

import avaliacao1.dao.ProdutoDAO;
import avaliacao1.model.Produto;

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvar(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            System.out.println("Nome do produto é obrigatório!");
            return false;
        }

        if (produto.getPreco_medio() <= 0) {
            System.out.println("Preço inválido!");
            return false;
        }

        if (produto.getQtde_estoque() < 0) {
            System.out.println("Estoque não pode ser negativo!");
            return false;
        }

        if (produto.getCategoria() == null || produto.getCategoria().getId() <= 0) {
            System.out.println("Categoria obrigatória!");
            return false;
        }

        return produtoDAO.salvar(produto);
    }

    public boolean alterar(Produto produto) {

        if (produto.getId() <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return produtoDAO.alterar(produto);
    }

    public boolean excluir(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return produtoDAO.excluir(id);
    }

    public Produto pesquisar(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        Produto produto = produtoDAO.pesquisar(id);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
        }

        return produto;
    }
}
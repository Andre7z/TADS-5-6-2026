package avaliacao1.controller;

import avaliacao1.dao.ProdutoDAO;
import avaliacao1.model.Produto;

import java.util.List;

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvar(Produto produto) {
        return produtoDAO.salvar(produto);
    }

    public boolean alterar(Produto produto) {
        return produtoDAO.alterar(produto);
    }

    public boolean excluir(int id) {
        return produtoDAO.excluir(id);
    }

    public Produto pesquisar(int id) {
        return produtoDAO.pesquisar(id);
    }

    public List<Produto> pesquisarTodos() {
        return produtoDAO.pesquisarTodos();
    }

    public boolean realizarVenda(int idProduto, double quantidade, double valorVenda, String cpf) {
        Produto produto = produtoDAO.pesquisar(idProduto);

        if (produto == null) {
            return false;
        }

        if (produto.getQtde_estoque() < 1 || produto.getQtde_estoque() < quantidade) {
            return false;
        }

        int totalVendas = produtoDAO.restrigirVendas(cpf);
        if (totalVendas >= 3) {
            return false;
        }

        double novaQuantidade = produto.getQtde_estoque() - quantidade;
        produto.setQtde_estoque(novaQuantidade);

        produto.setValor_ultima_venda(valorVenda);

        return produtoDAO.alterar(produto);
    }

    public boolean realizarCompra(int idProduto, double quantidade, double valorCompra) {
        Produto produto = produtoDAO.pesquisar(idProduto);

        if (produto == null) {
            return false;
        }

        double novaQuantidade = produto.getQtde_estoque() + quantidade;
        produto.setQtde_estoque(novaQuantidade);

        produto.setValor_ultima_compra(valorCompra);

        double precoMedio = (produto.getPreco_medio() + valorCompra) / 2;
        produto.setPreco_medio(precoMedio);

        return produtoDAO.alterar(produto);
    }

    public boolean verificaEstoqueExistente(int idProduto) {
        Produto produto = produtoDAO.pesquisar(idProduto);

        if (produto == null) {
            return false;
        }

        return produto.getQtde_estoque() >= 1;
    }
}
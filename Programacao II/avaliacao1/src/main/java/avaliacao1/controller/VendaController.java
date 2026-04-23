package avaliacao1.controller;

import java.util.List;

import avaliacao1.dao.VendaDAO;
import avaliacao1.dao.ProdutoDAO;
import avaliacao1.model.Venda;
import avaliacao1.model.Produto;
import avaliacao1.model.ProdutoVenda;

public class VendaController {

    VendaDAO vendaDAO = new VendaDAO();
    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvar(Venda venda) {
        return vendaDAO.salvar(venda);
    }

    public boolean alterar(Venda venda) {
        return vendaDAO.alterar(venda);
    }

    public boolean excluir(int id) {
        return vendaDAO.excluir(id);
    }

    public List<Venda> pesquisarTodos() {
        return vendaDAO.pesquisarTodos();
    }

    public boolean realizarVenda(Venda venda) {

        int totalVendas = vendaDAO.restrigirVendas(venda.getCliente().getCpf());
        if (totalVendas >= 3) {
            return false;
        }

        for (ProdutoVenda pv : venda.getprodutos()) {

            Produto produto = produtoDAO.pesquisar(pv.getProduto().getId());

            if (produto == null) {
                return false;
            }

            if (produto.getQtde_estoque() < pv.getQuantidade()) {
                return false;
            }
        }

        for (ProdutoVenda pv : venda.getprodutos()) {

            Produto produto = produtoDAO.pesquisar(pv.getProduto().getId());

            double novaQuantidade = produto.getQtde_estoque() - pv.getQuantidade();
            produto.setQtde_estoque(novaQuantidade);

            produto.setValor_ultima_venda(pv.getPreco_unit());

            produtoDAO.alterar(produto);
        }

        return vendaDAO.salvar(venda);
    }

    public boolean verificaEstoque(Venda venda) {

        for (ProdutoVenda pv : venda.getprodutos()) {

            Produto produto = produtoDAO.pesquisar(pv.getProduto().getId());

            if (produto == null || produto.getQtde_estoque() < pv.getQuantidade()) {
                return false;
            }
        }

        return true;
    }
}
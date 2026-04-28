package avaliacao1.controller;

import java.util.List;

import avaliacao1.dao.VendaDAO;
import avaliacao1.dao.ProdutoDAO;
import avaliacao1.model.Venda;
import avaliacao1.model.Produto;
import avaliacao1.model.VendaProduto;

public class VendaController {

    VendaDAO vendaDAO = new VendaDAO();
    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean alterar(Venda venda) {
        return vendaDAO.alterar(venda);
    }

    public boolean excluir(int id) {
        return vendaDAO.excluir(id);
    }

    public List<Venda> pesquisarTodos() {
        return vendaDAO.pesquisarTodos();
    }

    public boolean salvar(Venda venda) {

        // verifica se o cliente já comprou 3 vezes
        int totalVendas = vendaDAO.restrigirVendas(venda.getCliente().getCpf());
        if (totalVendas >= 3) {
            return false;
        }

        // verifica estoque
        for (VendaProduto vp : venda.getprodutos()) {

            Produto produto = produtoDAO.pesquisar(vp.getProduto().getId());

            if (produto == null) return false;

            if (produto.getQtde_estoque() < vp.getQuantidade()) {
                return false;
            }
        }

        // atualiza estoque e ultima venda
        for (VendaProduto vp : venda.getprodutos()) {

            Produto produto = produtoDAO.pesquisar(vp.getProduto().getId());

            produto.setQtde_estoque(produto.getQtde_estoque() - vp.getQuantidade());
            produto.setValor_ultima_venda(vp.getPreco_unit());

            produtoDAO.alterar(produto);
        }

        // salva venda
        return vendaDAO.salvar(venda);
    }
}
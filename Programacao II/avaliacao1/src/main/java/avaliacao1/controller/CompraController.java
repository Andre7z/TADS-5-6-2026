package avaliacao1.controller;

import java.util.List;
import avaliacao1.model.Compra;
import avaliacao1.dao.CompraDAO;
import avaliacao1.dao.ProdutoDAO;



public class CompraController {

    CompraDAO CompraDAO = new CompraDAO();
    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvar(Compra Compra) {
        return CompraDAO.salvar(Compra);
    }

    public boolean alterar(Compra Compra) {
        return CompraDAO.alterar(Compra);
    }

    public boolean excluir(int id) {
        return CompraDAO.excluir(id);
    }

    public List<Compra> pesquisarTodos() {
        return CompraDAO.pesquisarTodos();
    }
}
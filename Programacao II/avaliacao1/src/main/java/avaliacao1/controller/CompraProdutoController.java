package avaliacao1.controller;

import avaliacao1.model.CompraProduto;
import avaliacao1.dao.CompraProdutoDAO;

public class CompraProdutoController {

    CompraProdutoDAO cpDAO = new CompraProdutoDAO();

    public boolean salvar(CompraProduto cp) {
        return cpDAO.salvar(cp);
    }

    public boolean alterar(CompraProduto cp) {
        return cpDAO.alterar(cp);
    }

    public boolean excluir(int id) {
        return cpDAO.excluir(id);
    }

}
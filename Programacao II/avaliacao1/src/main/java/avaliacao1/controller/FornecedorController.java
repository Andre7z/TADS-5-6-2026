package avaliacao1.controller;

import avaliacao1.dao.FornecedorDAO;
import avaliacao1.model.Fornecedor;

public class FornecedorController {

    FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public boolean salvar(Fornecedor fornecedor) {
        return fornecedorDAO.salvar(fornecedor);
    }

    public boolean alterar(Fornecedor fornecedor) {
        return fornecedorDAO.alterar(fornecedor);
    }

    public boolean excluir(int id) {
        return fornecedorDAO.excluir(id);
    }

}
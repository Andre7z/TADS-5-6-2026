package avaliacao1.controller;

import avaliacao1.dao.FornecedorDAO;
import avaliacao1.model.Fornecedor;

public class FornecedorController {

    FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public boolean salvar(Fornecedor fornecedor) {

        if (fornecedor.getNome_fantasia() == null || fornecedor.getNome_fantasia().isEmpty()) {
            System.out.println("Nome fantasia é obrigatório!");
            return false;
        }

        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty()) {
            System.out.println("CNPJ é obrigatório!");
            return false;
        }

        return fornecedorDAO.salvar(fornecedor);
    }

    public boolean alterar(Fornecedor fornecedor) {

        if (fornecedor.getId() <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return fornecedorDAO.alterar(fornecedor);
    }

    public boolean excluir(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return fornecedorDAO.excluir(id);
    }

    public Fornecedor pesquisar(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        Fornecedor fornecedor = fornecedorDAO.pesquisar(id);

        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado!");
        }

        return fornecedor;
    }
}
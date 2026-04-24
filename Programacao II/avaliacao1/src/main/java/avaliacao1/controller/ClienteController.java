package avaliacao1.controller;

import avaliacao1.dao.ClienteDAO;
import avaliacao1.model.Cliente;

public class ClienteController {

    ClienteDAO clienteDAO = new ClienteDAO();

    public boolean salvar(Cliente cliente) {
        return clienteDAO.salvar(cliente);
    }

    public boolean alterar(Cliente cliente) {
        return clienteDAO.alterar(cliente);
    }

    public boolean excluir(int id) {

        return clienteDAO.excluir(id);
    }
}
package primeiro_projeto_p2.controller;

import primeiro_projeto_p2.dao.ClienteDAO;
import primeiro_projeto_p2.model.Cliente;

public class ClienteController {

    public boolean pesquisar() {
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean resultado = clienteDAO.pesquisar();
        return resultado;
    }

    public boolean salvar(Cliente cliente) {
        System.out.println("Salvando cliente " + cliente.getNome() + " no controller");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.salvar(cliente);
        return true;
    }
}

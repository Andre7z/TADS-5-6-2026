package primeiro_maven_pii.controller;

import primeiro_maven_pii.dao.ClienteDAO;
import primeiro_maven_pii.model.Cliente;

public class ClienteController {
    public void salvar(Cliente cliente) {
        System.out.println("Cliente salvo no Controller!");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.salvar(cliente);
    }
    
}
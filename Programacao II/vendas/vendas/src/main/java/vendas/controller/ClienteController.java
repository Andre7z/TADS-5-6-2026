package vendas.controller;

import vendas.dao.ClienteDAO;
import vendas.model.Cliente;

public class ClienteController {

    ClienteDAO clienteDAO = new ClienteDAO();

    // 💾 SALVAR
    public boolean salvar(Cliente cliente) {

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            System.out.println("Nome do cliente é obrigatório!");
            return false;
        }

        return clienteDAO.salvar(cliente);
    }

    // 🔄 ALTERAR
    public boolean alterar(Cliente cliente) {

        if (cliente.getId() <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return clienteDAO.alterar(cliente);
    }

    // ❌ EXCLUIR
    public boolean excluir(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return clienteDAO.excluir(id);
    }

    // 🔍 PESQUISAR (por ID)
    public Cliente pesquisar(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        Cliente cliente = clienteDAO.pesquisar(id);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
        }

        return cliente;
    }
}
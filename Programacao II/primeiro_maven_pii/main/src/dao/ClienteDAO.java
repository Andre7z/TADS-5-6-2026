package primeiro_maven_pii.dao;

import primeiro_maven_pii.model.Cliente;

public class ClienteDAO {
    public void salvar(Cliente cliente) {
        System.out.println("Cliente " + cliente.getNome() + " salvo no banco de dados.");
    }
    
}
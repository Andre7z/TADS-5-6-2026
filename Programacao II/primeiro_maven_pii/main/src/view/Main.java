package primeiro_maven_pii.view;

import java.sql.Connection;
import java.sql.ResultSet;

import primeiro_maven_pii.controller.ClienteController;
import primeiro_maven_pii.dao.Conexao;
import java.sql.Statement;
import primeiro_maven_pii.model.Cliente;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("João");
        cliente.setEmail("joao@gmail.com");
        ClienteController clienteController = new ClienteController();
        clienteController.salvar(cliente);
        

        Connection conn = null;
        try {
            conn = Conexao.getConnection()
            System.out.println("Conectado com sucesso!");
            Statment stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");
            if (rs.next()){
                System.out.println("Dados do cliente:" + rs.getString(1) + "" + rs.getString(5));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();

    }
    
}


}
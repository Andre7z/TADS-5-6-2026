package primeiro_projeto_p2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import primeiro_projeto_p2.model.Cliente;

public class ClienteDAO {
    Connection conn = null;

    public boolean pesquisar() {
        try {
            conn = Conexao.getConnection();
            System.out.println("Conectado com sucesso!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT*from cliente");
            if (rs.next()) {
                System.out.println("Dados do banco: " + rs.getString(1) + " " +
                        rs.getString(5));
            }
            rs.close();
            stmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public boolean salvar(Cliente cliente) {
        System.out.println("Salvando cliente " + cliente.getNome() + " no banco de dados.");
        return true;
    }
}

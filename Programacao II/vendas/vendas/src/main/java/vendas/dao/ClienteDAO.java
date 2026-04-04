package vendas.dao;

import vendas.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    Connection conn = null;

    // 💾 SALVAR
    public boolean salvar(Cliente cliente) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO cliente (nome, cpf, rg, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getEndereco());
            ps.setString(5, cliente.getTelefone());

            ps.executeUpdate();
            ps.close();

            System.out.println("Cliente salvo com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    // ❌ EXCLUIR
    public boolean excluir(int id) {
        try {
            conn = Conexao.getConnection();

            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("Cliente excluído com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    // 🔄 ALTERAR
    public boolean alterar(Cliente cliente) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE cliente SET nome=?, cpf=?, rg=?, endereco=?, telefone=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getEndereco());
            ps.setString(5, cliente.getTelefone());
            ps.setInt(6, cliente.getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("Cliente alterado com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

public Cliente pesquisar(int id) {
    Cliente cliente = null;

    try {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM cliente WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setRg(rs.getString("rg"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setTelefone(rs.getString("telefone"));
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Conexao.fecharConexao();
    }

    return cliente;
}
}
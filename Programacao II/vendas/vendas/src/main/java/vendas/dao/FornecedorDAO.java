package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.Fornecedor;

public class FornecedorDAO {
    Connection conn = null;

    public boolean salvar(Fornecedor fornecedor) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO FORNECEDOR (nome_fantasia, razao_social, cnpj) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, fornecedor.getNome_fantasia());
            ps.setString(2, fornecedor.getRazao_social());
            ps.setString(3, fornecedor.getCnpj());

            ps.executeUpdate();
            ps.close();

            System.out.println("fornecedor salva com sucesso!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public boolean excluir(int id) {
        try {
            conn = Conexao.getConnection();

            String sql = "DELETE FROM fornecedor WHERE ID = ? ";
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

    public boolean alterar(Fornecedor fornecedor) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE fornecedor SET nome=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, fornecedor.getNome_fantasia());
            ps.setString(2, fornecedor.getRazao_social());
            ps.setString(3, fornecedor.getCnpj());

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

public Fornecedor pesquisar(int id) {
    Fornecedor fornecedor = null;

    try {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM fornecedor WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            fornecedor = new Fornecedor();

            fornecedor.setId(rs.getInt("id"));
            fornecedor.setNome_fantasia(rs.getString("nome_fantasia"));
            fornecedor.setRazao_social(rs.getString("razao_social"));
            fornecedor.setCnpj(rs.getString("cnpj"));
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Conexao.fecharConexao();
    }

    return fornecedor;
}
}
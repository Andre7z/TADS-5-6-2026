package avaliacao1.dao;

import avaliacao1.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoriaDAO {

    Connection conn = null;

    public boolean salvar(Categoria categoria) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO CATEGORIA (nome) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, categoria.getNome());

            ps.executeUpdate();
            ps.close();

            System.out.println("Categoria salva com sucesso!");
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

            String sql = "DELETE FROM Categoria WHERE ID = ? ";
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

    public boolean alterar(Categoria categoria) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE categoria SET nome=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, categoria.getNome());

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

    public Categoria pesquisar(int id) {
    Categoria categoria = null;

    try {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM categoria WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Conexao.fecharConexao();
    }

    return categoria;
}
}

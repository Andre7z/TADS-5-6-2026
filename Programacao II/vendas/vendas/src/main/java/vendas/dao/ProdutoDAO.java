package vendas.dao;

import vendas.model.Produto;
import vendas.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProdutoDAO {

    Connection conn = null;

    public boolean salvar(Produto produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO produto (nome, preco, qtde_estoque, id_categoria) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setDouble(3, produto.getQtde_estoque());
            ps.setInt(4, produto.getCategoria().getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("produto salva com sucesso!");
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

            String sql = "DELETE FROM produto WHERE ID = ? ";
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

    public boolean alterar(Produto produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE produto SET nome=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setDouble(3, produto.getQtde_estoque());
            ps.setInt(4, produto.getCategoria().getId());

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
public Produto pesquisar(int id) {
    Produto produto = null;

    try {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM produto WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            produto = new Produto();

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQtde_estoque(rs.getDouble("qtde_estoque"));

            // Categoria
            Categoria c = new Categoria();
            c.setId(rs.getInt("id_categoria"));

            produto.setCategoria(c);
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Conexao.fecharConexao();
    }

    return produto;
}
}
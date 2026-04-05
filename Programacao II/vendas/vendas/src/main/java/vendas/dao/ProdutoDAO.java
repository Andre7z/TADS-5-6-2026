package vendas.dao;

import vendas.model.Produto;
import vendas.model.Categoria;
import vendas.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    Connection conn = null;

    public boolean salvar(Produto produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO produto (nome, preco, qtde_estoque, id_categoria) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setDouble(3, produto.getQtde_estoque());
            ps.setInt(4, produto.getCategoria().getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }

            ps.close();

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

            String sql = "DELETE FROM produto WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

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

            String sql = "UPDATE produto SET nome=?, preco=?, qtde_estoque=?, id_categoria=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setDouble(3, produto.getQtde_estoque());
            ps.setInt(4, produto.getCategoria().getId());
            ps.setInt(5, produto.getId());

            ps.executeUpdate();
            ps.close();

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

                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                produto.setCategoria(cat);
            }

            rs.close();
            ps.close();

            if (produto != null) {

                String sqlF = "SELECT f.id, f.nome_fantasia FROM fornecedor f "
                        + "JOIN fornecedor_produto fp ON f.id = fp.id_fornecedor "
                        + "WHERE fp.id_produto = ?";

                PreparedStatement psF = conn.prepareStatement(sqlF);
                psF.setInt(1, id);

                ResultSet rsF = psF.executeQuery();

                List<Fornecedor> lista = new ArrayList<>();

                while (rsF.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setId(rsF.getInt("id"));
                    f.setNome_fantasia(rsF.getString("nome_fantasia"));

                    lista.add(f);
                }

                produto.setFornecedores(lista);

                rsF.close();
                psF.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }

        return produto;
    }
}
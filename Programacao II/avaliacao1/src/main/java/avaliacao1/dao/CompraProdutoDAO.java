package avaliacao1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import avaliacao1.model.CompraProduto;
import avaliacao1.model.Produto;
import avaliacao1.model.Compra;

public class CompraProdutoDAO {

    Connection conn = null;

    public boolean salvar(CompraProduto produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO produto_Compra (id_Compra, id_produto, quantidade, preco_unit) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, produto.getCompra().getId());
            ps.setInt(2, produto.getProduto().getId());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPreco_unit());

            int qtdeLinhas = ps.executeUpdate();
            ps.close();
            return qtdeLinhas > 0;
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

            String sql = "DELETE FROM produto_Compra WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            int qtdeLinhas = ps.executeUpdate();
            ps.close();
            return qtdeLinhas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public boolean alterar(CompraProduto produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE produto_Compra SET quantidade=?, preco_unit=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, produto.getQuantidade());
            ps.setDouble(2, produto.getPreco_unit());
            ps.setInt(3, produto.getId());

            int qtdeLinhas = ps.executeUpdate();
            ps.close();

            return qtdeLinhas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public List<CompraProduto> pesquisarTodos() {
        try {
            List<CompraProduto> compras = new ArrayList<>();

            conn = Conexao.getConnection();

            String sql = "SELECT * FROM produto_Compra WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CompraProduto cp = new CompraProduto();

                cp.setId(rs.getInt("id"));
                cp.setQuantidade(rs.getInt("quantidade"));
                cp.setPreco_unit(rs.getDouble("preco_unit"));

                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));

                Compra c = new Compra();
                c.setId(rs.getInt("id_Compra"));

                cp.setProduto(p);
                cp.setCompra(c);
            }

            rs.close();
            ps.close();
            return compras;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Conexao.fecharConexao();
        }
    }
}

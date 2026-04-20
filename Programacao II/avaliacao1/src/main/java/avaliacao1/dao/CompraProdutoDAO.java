package avaliacao1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

            ps.executeUpdate();
            ps.close();

            System.out.println("CompraProduto salvo!");
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

            String sql = "DELETE FROM produto_Compra WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("CompraProduto excluído!");
            return true;

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

            ps.executeUpdate();
            ps.close();

            System.out.println("CompraProduto alterado!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public CompraProduto pesquisar(int id) {
        CompraProduto produto = null;

        try {
            conn = Conexao.getConnection();

            String sql = "SELECT * FROM produto_Compra WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produto = new CompraProduto();

                produto.setId(rs.getInt("id"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco_unit(rs.getDouble("preco_unit"));

                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));

                Compra c = new Compra();
                c.setId(rs.getInt("id_Compra"));

                produto.setProduto(p);
                produto.setCompra(c);
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
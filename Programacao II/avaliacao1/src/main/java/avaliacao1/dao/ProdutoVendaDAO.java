package avaliacao1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import avaliacao1.model.ProdutoVenda;
import avaliacao1.model.Produto;
import avaliacao1.model.Venda;

public class ProdutoVendaDAO {

    Connection conn = null;

    public boolean salvar(ProdutoVenda produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO produto_venda (id_venda, id_produto, quantidade, preco_unit) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, produto.getVenda().getId());
            ps.setInt(2, produto.getProduto().getId());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPreco_unit());

            ps.executeUpdate();
            ps.close();

            System.out.println("produtoVenda salvo!");
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

            String sql = "DELETE FROM produto_venda WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("produtoVenda excluído!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }


    public boolean alterar(ProdutoVenda produto) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE produto_venda SET quantidade=?, preco_unit=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, produto.getQuantidade());
            ps.setDouble(2, produto.getPreco_unit());
            ps.setInt(3, produto.getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("produtoVenda alterado!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public ProdutoVenda pesquisar(int id) {
        ProdutoVenda produto = null;

        try {
            conn = Conexao.getConnection();

            String sql = "SELECT * FROM produto_venda WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produto = new ProdutoVenda();

                produto.setId(rs.getInt("id"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco_unit(rs.getDouble("preco_unit"));

                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));

                Venda v = new Venda();
                v.setId(rs.getInt("id_venda"));

                produto.setProduto(p);
                produto.setVenda(v);
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
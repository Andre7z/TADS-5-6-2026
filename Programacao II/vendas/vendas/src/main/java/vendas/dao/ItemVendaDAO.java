package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vendas.model.ItemVenda;
import vendas.model.Produto;
import vendas.model.Venda;

public class ItemVendaDAO {

    Connection conn = null;

    public boolean salvar(ItemVenda item) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO item_venda (id_venda, id_produto, quantidade, preco_unit) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, item.getVenda().getId());
            ps.setInt(2, item.getProduto().getId());
            ps.setInt(3, item.getQuantidade());
            ps.setDouble(4, item.getPreco_unit());

            ps.executeUpdate();
            ps.close();

            System.out.println("ItemVenda salvo!");
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

            String sql = "DELETE FROM item_venda WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("ItemVenda excluído!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }


    public boolean alterar(ItemVenda item) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE item_venda SET quantidade=?, preco_unit=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, item.getQuantidade());
            ps.setDouble(2, item.getPreco_unit());
            ps.setInt(3, item.getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("ItemVenda alterado!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public ItemVenda pesquisar(int id) {
        ItemVenda item = null;

        try {
            conn = Conexao.getConnection();

            String sql = "SELECT * FROM item_venda WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new ItemVenda();

                item.setId(rs.getInt("id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco_unit(rs.getDouble("preco_unit"));

                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));

                Venda v = new Venda();
                v.setId(rs.getInt("id_venda"));

                item.setProduto(p);
                item.setVenda(v);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }

        return item;
    }

    public List<ItemVenda> listarPorVenda(int idVenda) {
        List<ItemVenda> lista = new ArrayList<>();

        try {
            conn = Conexao.getConnection();

            String sql = "SELECT * FROM item_venda WHERE id_venda=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idVenda);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda(); 

                item.setId(rs.getInt("id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco_unit(rs.getDouble("preco_unit"));

                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));

                Venda v = new Venda();
                v.setId(rs.getInt("id_venda"));

                item.setProduto(p);
                item.setVenda(v);

                lista.add(item);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }

        return lista;
    }
}
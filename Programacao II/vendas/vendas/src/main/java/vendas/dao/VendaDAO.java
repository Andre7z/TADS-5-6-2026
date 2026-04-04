package vendas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.Cliente;
import vendas.model.Venda;

public class VendaDAO {

    Connection conn = null;

    // 💾 SALVAR
    public boolean salvar(Venda venda) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO venda (data_venda, valor_total, id_cliente) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(venda.getData_venda()));
            ps.setDouble(2, venda.getValor_total());
            ps.setInt(3, venda.getCliente().getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venda.setId(rs.getInt("id")); // 🔥 pega o ID gerado
            }

            rs.close();
            ps.close();

            System.out.println("Venda salva com sucesso!");
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

            String sql = "DELETE FROM venda WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("Venda excluída com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    // 🔄 ALTERAR
    public boolean alterar(Venda venda) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE venda SET dt_venda=?, valor_total=?, id_cliente=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(venda.getData_venda()));
            ps.setDouble(2, venda.getValor_total());
            ps.setInt(3, venda.getCliente().getId());
            ps.setInt(4, venda.getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("Venda alterada com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

   public Venda pesquisar(int id) {
    Venda venda = null;

    try {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM venda WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            venda = new Venda();

            venda.setId(rs.getInt("id"));
            venda.setData_venda(rs.getDate("dt_venda").toLocalDate());
            venda.setValor_total(rs.getDouble("valor_total"));

            // 🔥 Cliente
            Cliente c = new Cliente();
            c.setId(rs.getInt("id_cliente"));

            venda.setCliente(c);
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Conexao.fecharConexao();
    }

    return venda;
}
}
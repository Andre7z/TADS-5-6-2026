package avaliacao1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import avaliacao1.model.Fornecedor;
import avaliacao1.model.Compra;

public class CompraDAO {

    Connection conn = null;

    public boolean salvar(Compra Compra) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO Compra (data_Compra, valor_total, id_Fornecedor) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(Compra.getData_compra()));
            ps.setDouble(2, Compra.getValor_total());
            ps.setInt(3, Compra.getFornecedor().getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Compra.setId(rs.getInt("id")); 
            }

            rs.close();
            ps.close();

            System.out.println("Compra salva com sucesso!");
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

            String sql = "DELETE FROM Compra WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            System.out.println("Compra excluída com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public boolean alterar(Compra Compra) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE Compra SET dt_Compra=?, valor_total=?, id_Fornecedor=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(Compra.getData_compra()));
            ps.setDouble(2, Compra.getValor_total());
            ps.setInt(3, Compra.getFornecedor().getId());
            ps.setInt(4, Compra.getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("Compra alterada com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

   public Compra pesquisar(int id) {
    Compra Compra = null;

    try {
        conn = Conexao.getConnection();

        String sql = "SELECT * FROM Compra WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Compra = new Compra();

            Compra.setId(rs.getInt("id"));
            Compra.setData_compra(rs.getDate("dt_Compra").toLocalDate());
            Compra.setValor_total(rs.getDouble("valor_total"));
            
            Fornecedor f = new Fornecedor();
            f.setId(rs.getInt("id_Fornecedor"));

            Compra.setFornecedor(f);
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Conexao.fecharConexao();
    }

    return Compra;
}
}
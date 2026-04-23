package avaliacao1.dao;

import avaliacao1.model.Compra;
import avaliacao1.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    Connection conn = null;

    public boolean salvar(Compra compra) {
        try {
            conn = Conexao.getConnection();

            //valida quantidade de Compras do Fornecedor
            if (!verificaQtdeCompras(compra.getFornecedor().getId())) {
                System.out.println("Fornecedor já atingiu o limite de Compras!");
                return false;
            }

            String sql = "INSERT INTO compra (data_Compra, valor_total, Fornecedor_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, java.sql.Date.valueOf(compra.getData_compra()));
            ps.setDouble(2, compra.getValor_total());
            ps.setInt(3, compra.getFornecedor().getId());

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

            String sql = "DELETE FROM compra WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

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

    public boolean alterar(Compra compra) {
        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE compra SET data_Compra = ?, valor_total = ?, Fornecedor_id = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, java.sql.Date.valueOf(compra.getData_compra()));
            ps.setDouble(2, compra.getValor_total());
            ps.setInt(3, compra.getFornecedor().getId());
            ps.setInt(4, compra.getId());

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

    public List<Compra> pesquisarTodos() {
        try {
            List<Compra> compras = new ArrayList<>();

            conn = Conexao.getConnection();

            String sql = "SELECT * FROM compra";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                Fornecedor fornecedor = new Fornecedor();

                compra.setId(rs.getInt("id"));
                compra.setData_compra(rs.getDate("data_compra").toLocalDate());
                compra.setValor_total(rs.getDouble("valor_total"));

                fornecedor.setId(rs.getInt("fornecedor_id"));
                compra.setFornecedor(fornecedor);

                compras.add(compra);
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

    public boolean verificaQtdeCompras(int fornecedorId) {
        try {
            conn = Conexao.getConnection();
            System.out.println("Conectado com sucesso!");

            String sql = "SELECT COUNT(*) AS total FROM compra WHERE Fornecedor_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, fornecedorId);

            ResultSet rs = ps.executeQuery();

            int qtdeCompras = 0;

            if (rs.next()) {
                qtdeCompras = rs.getInt("total");
            }

            rs.close();
            ps.close();

            return qtdeCompras < 3;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }
} 
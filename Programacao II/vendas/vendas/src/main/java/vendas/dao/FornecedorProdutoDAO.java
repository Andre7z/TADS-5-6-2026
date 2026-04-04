package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vendas.model.FornecedorProduto;

public class FornecedorProdutoDAO {

    Connection conn = null;

    public boolean salvar(FornecedorProduto fp) {
        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO fornecedor_produto (id_fornecedor, id_produto) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, fp.getFornecedor().getId());
            ps.setInt(2, fp.getProduto().getId());

            ps.executeUpdate();
            ps.close();

            System.out.println("Relacionamento salvo!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }
}
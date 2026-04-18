package avaliacao1.dao;

import avaliacao1.model.Produto;
import avaliacao1.model.Categoria;
import avaliacao1.model.Fornecedor;
import avaliacao1.model.FornecedorProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean salvar(Produto produto) {
        Connection conn = null;

        try {
            conn = Conexao.getConnection();

            String sql = "INSERT INTO produto (nome, preco, qtde_estoque, id_categoria, valor_ultima_compra, valor_ultima_venda) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco_medio());
            ps.setDouble(3, produto.getQtde_estoque());
            ps.setInt(4, produto.getCategoria().getId());
            ps.setDouble(5, produto.getValor_ultima_compra());
            ps.setDouble(6, produto.getValor_ultima_venda());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();

            // 🔥 SALVAR N:N
            if (produto.getFornecedores() != null) {
                String sqlFP = "INSERT INTO fornecedor_produto (id_fornecedor, id_produto) VALUES (?, ?)";
                PreparedStatement psFP = conn.prepareStatement(sqlFP);

                for (FornecedorProduto fp : produto.getFornecedores()) {
                    psFP.setInt(1, fp.getFornecedor().getId());
                    psFP.setInt(2, produto.getId());
                    psFP.executeUpdate();
                }

                psFP.close();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public boolean excluir(int id) {
        Connection conn = null;

        try {
            conn = Conexao.getConnection();

            String sqlFP = "DELETE FROM fornecedor_produto WHERE id_produto=?";
            PreparedStatement psFP = conn.prepareStatement(sqlFP);
            psFP.setInt(1, id);
            psFP.executeUpdate();
            psFP.close();

            String sql = "DELETE FROM produto WHERE id=?";
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
        Connection conn = null;

        try {
            conn = Conexao.getConnection();

            String sql = "UPDATE produto SET nome=?, preco=?, qtde_estoque=?, id_categoria=?, valor_ultima_compra=?, valor_ultima_venda=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco_medio());
            ps.setDouble(3, produto.getQtde_estoque());
            ps.setInt(4, produto.getCategoria().getId());
            ps.setDouble(5, produto.getValor_ultima_compra());
            ps.setDouble(6, produto.getValor_ultima_venda());
            ps.setInt(7, produto.getId());

            ps.executeUpdate();
            ps.close();

            // 🔥 ATUALIZAR N:N
            String del = "DELETE FROM fornecedor_produto WHERE id_produto=?";
            PreparedStatement psDel = conn.prepareStatement(del);
            psDel.setInt(1, produto.getId());
            psDel.executeUpdate();
            psDel.close();

            if (produto.getFornecedores() != null) {
                String ins = "INSERT INTO fornecedor_produto (id_fornecedor, id_produto) VALUES (?, ?)";
                PreparedStatement psIns = conn.prepareStatement(ins);

                for (FornecedorProduto fp : produto.getFornecedores()) {
                    psIns.setInt(1, fp.getFornecedor().getId());
                    psIns.setInt(2, produto.getId());
                    psIns.executeUpdate();
                }

                psIns.close();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public Produto pesquisar(int id) {
        Connection conn = null;
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
                produto.setPreco_medio(rs.getDouble("preco"));
                produto.setQtde_estoque(rs.getDouble("qtde_estoque"));
                produto.setValor_ultima_compra(rs.getDouble("valor_ultima_compra"));
                produto.setValor_ultima_venda(rs.getDouble("valor_ultima_venda"));

                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                produto.setCategoria(cat);
            }

            rs.close();
            ps.close();

            // 🔥 BUSCAR N:N
            if (produto != null) {

                String sqlF = "SELECT f.id, f.nome_fantasia FROM fornecedor f "
                        + "JOIN fornecedor_produto fp ON f.id = fp.id_fornecedor "
                        + "WHERE fp.id_produto = ?";

                PreparedStatement psF = conn.prepareStatement(sqlF);
                psF.setInt(1, id);

                ResultSet rsF = psF.executeQuery();

                List<FornecedorProduto> lista = new ArrayList<>();

                while (rsF.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setId(rsF.getInt("id"));
                    f.setNome_fantasia(rsF.getString("nome_fantasia"));

                    FornecedorProduto fp = new FornecedorProduto();
                    fp.setFornecedor(f);
                    fp.setProduto(produto);

                    lista.add(fp);
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
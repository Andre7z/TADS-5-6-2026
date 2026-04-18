package avaliacao1.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import avaliacao1.dao.Conexao;
import avaliacao1.dao.ProdutoVendaDAO;
import avaliacao1.dao.VendaDAO;
import avaliacao1.model.ProdutoVenda;
import avaliacao1.model.Venda;

public class VendaController {

    VendaDAO vendaDAO = new VendaDAO();
    ProdutoVendaDAO itemDAO = new ProdutoVendaDAO();

    public boolean realizarVenda(Venda venda, List<ProdutoVenda> itens) {

        Connection conn = null;

        try {
            conn = Conexao.getConnection();

            int idCliente = venda.getCliente().getId();

            String sqlNome = "SELECT nome FROM cliente WHERE id = ?";
            PreparedStatement psNome = conn.prepareStatement(sqlNome);
            psNome.setInt(1, idCliente);

            ResultSet rsNome = psNome.executeQuery();

            if (rsNome.next()) {

                String nome = rsNome.getString("nome");

                if (nome.equalsIgnoreCase("Flávio Vilela")) {

                    String sqlCheck = "SELECT COUNT(*) FROM venda WHERE id_cliente = ?";
                    PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
                    psCheck.setInt(1, idCliente);

                    ResultSet rsCheck = psCheck.executeQuery();

                    if (rsCheck.next() && rsCheck.getInt(1) >= 3) {
                        System.out.println("Cliente Flávio Vilela atingiu limite de avaliacao1!");
                        return false;
                    }

                    rsCheck.close();
                    psCheck.close();
                }
            }

            rsNome.close();
            psNome.close();

            for (ProdutoVenda item : itens) {

                String sqlEstoque = "SELECT qtde_estoque FROM produto WHERE id=?";
                PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque);
                psEstoque.setInt(1, item.getProduto().getId());

                ResultSet rsEstoque = psEstoque.executeQuery();

                if (rsEstoque.next()) {

                    double estoque = rsEstoque.getDouble("qtde_estoque");

                    if (estoque < item.getQuantidade() || estoque < 1) {
                        System.out.println("Estoque insuficiente para produto ID: " + item.getProduto().getId());
                        return false;
                    }
                }

                rsEstoque.close();
                psEstoque.close();
            }

            double total = 0;

            for (ProdutoVenda item : itens) {
                total += item.getQuantidade() * item.getPreco_unit();
            }

            venda.setValor_total(total);

            vendaDAO.salvar(venda);

            for (ProdutoVenda item : itens) {

                item.setVenda(venda);
                itemDAO.salvar(item);

                String sqlUpdate = "UPDATE produto SET qtde_estoque = qtde_estoque - ? WHERE id=?";
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

                psUpdate.setInt(1, item.getQuantidade());
                psUpdate.setInt(2, item.getProduto().getId());

                psUpdate.executeUpdate();
                psUpdate.close();
            }

            System.out.println("Venda realizada com sucesso!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexao.fecharConexao();
        }
    }

    public boolean salvar(Venda venda) {
        try {
            vendaDAO.salvar(venda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Venda venda) {
        try {
            vendaDAO.alterar(venda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            vendaDAO.excluir(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Venda pesquisar(int id) {
        try {
            return vendaDAO.pesquisar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
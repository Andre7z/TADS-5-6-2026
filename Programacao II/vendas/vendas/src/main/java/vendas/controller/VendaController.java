package vendas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import vendas.dao.Conexao;
import vendas.dao.ItemVendaDAO;
import vendas.dao.VendaDAO;
import vendas.model.ItemVenda;
import vendas.model.Venda;

public class VendaController {

    VendaDAO vendaDAO = new VendaDAO();
    ItemVendaDAO itemDAO = new ItemVendaDAO();

    public boolean realizarVenda(Venda venda, List<ItemVenda> itens) {

        Connection conn = null;

        try {
            conn = Conexao.getConnection();

            // 🔴 REGRA 1: limite de vendas para Flávio Vilela
            if (venda.getCliente().getNome().equalsIgnoreCase("Flávio Vilela")) {

                String sqlCheck = "SELECT COUNT(*) FROM venda v "
                        + "JOIN cliente c ON v.id_cliente = c.id "
                        + "WHERE c.nome = ?";

                PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
                psCheck.setString(1, "Flávio Vilela");

                ResultSet rsCheck = psCheck.executeQuery();

                if (rsCheck.next() && rsCheck.getInt(1) >= 3) {
                    System.out.println("Cliente Flávio Vilela atingiu limite de vendas!");
                    return false;
                }
            }

            // 🔴 REGRA 2: validar estoque
            for (ItemVenda item : itens) {

                String sqlEstoque = "SELECT qtde_estoque FROM produto WHERE id=?";
                PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque);
                psEstoque.setInt(1, item.getProduto().getId());

                ResultSet rsEstoque = psEstoque.executeQuery();

                if (rsEstoque.next()) {
                    if (rsEstoque.getDouble(1) < item.getQuantidade()) {
                        System.out.println("Estoque insuficiente para produto ID: " + item.getProduto().getId());
                        return false;
                    }
                }
            }

            // 🔴 REGRA 3: calcular total da venda
            double total = 0;

            for (ItemVenda item : itens) {
                total += item.getQuantidade() * item.getPreco_unit();
            }

            venda.setValor_total(total);

            // 🔴 salvar venda
            vendaDAO.salvar(venda);

            // 🔴 salvar itens + atualizar estoque
            for (ItemVenda item : itens) {

                item.setVenda(venda);
                itemDAO.salvar(item);

                String sqlUpdate = "UPDATE produto SET qtde_estoque = qtde_estoque - ? WHERE id=?";
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

                psUpdate.setInt(1, item.getQuantidade());
                psUpdate.setInt(2, item.getProduto().getId());

                psUpdate.executeUpdate();
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
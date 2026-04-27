package avaliacao1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import avaliacao1.controller.*;
import avaliacao1.model.*;
import avaliacao1.dao.*;

public class Main {
    public static void main(String[] args) {

        VendaController vendaController = new VendaController();
        ProdutoController produtoController = new ProdutoController();
        CompraController compraController = new CompraController();

        // categoria
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria1 = new Categoria(1, "Eletrônicos");
        categoriaDAO.salvar(categoria1);

        // cliente
        ClienteDAO clienteDAO = new ClienteDAO(); // faltava ;
        Cliente cliente1 = new Cliente(1, "João", "123", "sim", "Rua 1, Bairro a", "99900-9900");
        clienteDAO.salvar(cliente1);

        // fornecedor
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor1 = new Fornecedor(1, "Fornecedor A", "111", "11/11002233-7890");
        fornecedorDAO.salvar(fornecedor1);

        // produto
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto1 = new Produto(1, "Notebook", 0.0, 0, categoria1, 0.0, 0.0);
        produtoDAO.salvar(produto1);

        // fornecedor produto
        FornecedorProdutoDAO fornecedorProdutoDAO = new FornecedorProdutoDAO();
        FornecedorProduto fp1 = new FornecedorProduto(1, fornecedor1, produto1);
        fornecedorProdutoDAO.salvar(fp1); // corrigido (estava salvando fornecedor errado)

        // compra
        CompraDAO compraDAO = new CompraDAO();
        Compra compra1 = new Compra(1, LocalDate.now(), 0.0, fornecedor1);
        compraDAO.salvar(compra1);

        // item da compra
        CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
        CompraProduto cp1 = new CompraProduto(1, produto1, compra1, 5, 3000.00);
        compraProdutoDAO.salvar(cp1);

        List<CompraProduto> compraProdutos = new ArrayList<>();
        compraProdutos.add(cp1);
        compra1.setProdutos(compraProdutos);

        // atualiza produto (compra)
        produto1.setQtde_estoque(produto1.getQtde_estoque() + cp1.getQuantidade());
        produto1.setValor_ultima_compra(cp1.getPreco_unit());
        produto1.setPreco_medio(cp1.getPreco_unit());
        produtoDAO.alterar(produto1); // importante

        // venda
        VendaDAO vendaDAO = new VendaDAO();
        Venda venda1 = new Venda(1, LocalDate.now(), 60000.00, cliente1);
        vendaDAO.salvar(venda1);

        // item da venda
        ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
        ProdutoVenda vp1 = new ProdutoVenda(1, venda1, produto1, 2, 3000.00);

        if (produtoController.verificaEstoqueExistente(vp1.getProduto())) {
            System.out.println("Produto 1 ok");
        } else {
            System.out.println("Produto 1 sem estoque");
        }

        produtoVendaDAO.salvar(vp1); // estava faltando salvar o item

        List<ProdutoVenda> vendaProdutos = new ArrayList<>();
        vendaProdutos.add(vp1);
        venda1.setprodutos(vendaProdutos);

        // atualiza produto (venda)
        produto1.setQtde_estoque(produto1.getQtde_estoque() - vp1.getQuantidade());
        produto1.setValor_ultima_venda(vp1.getPreco_unit());
        produtoDAO.alterar(produto1); // importante

        // pode manter controller ou não, mas já salvou direto
        vendaController.salvar(venda1);
    }
}
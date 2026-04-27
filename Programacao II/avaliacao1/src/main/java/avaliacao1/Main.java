package avaliacao1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import avaliacao1.controller.CompraController;
import avaliacao1.controller.ProdutoController;
import avaliacao1.controller.VendaController;
import avaliacao1.model.*;

public class Main {
    public static void main(String[] args) {

        VendaController vendaController = new VendaController();
        ProdutoController produtoController = new ProdutoController();
        CompraController compraController = new CompraController();

        // categorias
        Categoria categoria1 = new Categoria(1, "Eletrônicos");
        Categoria categoria2 = new Categoria(2, "Eletrodomésticos");

        // cliente
        Cliente cliente1 = new Cliente(1, "João", "123", "sim", "Rua 1, Bairro a", "99900-9900");

        // fornecedor
        Fornecedor fornecedor1 = new Fornecedor(1, "Fornecedor A", "111", "11/11002233-7890");
        
        // produtos
        Produto produto1 = new Produto(1, "Notebook", 0.0, 0, categoria1, 0.0, 0.00);
        Produto produto2 = new Produto(2, "Geladeira", 0.0, 0, categoria2, 0.0, 0.0);

        // fornecedor produto
        FornecedorProduto fp1 = new FornecedorProduto(1, fornecedor1, produto1);
        FornecedorProduto fp2 = new FornecedorProduto(2, fornecedor1, produto2);

        // compra
        Compra compra1 = new Compra(1, LocalDate.now(), 0.0 ,  fornecedor1);

        CompraProduto cp1 = new CompraProduto(1, produto1, compra1, 5, 3000.00);
        CompraProduto cp2 = new CompraProduto(2,produto2, compra1, 3, 2000.00);

        List<CompraProduto> compraProdutos = new ArrayList<>();
        compraProdutos.add(cp1);
        compraProdutos.add(cp2);

        compra1.setProdutos(compraProdutos);

        // atualiza produto (compra)
        produto1.setQtde_estoque(produto1.getQtde_estoque() + cp1.getQuantidade());
        produto1.setValor_ultima_compra(cp1.getPreco_unit());
        produto1.setPreco_medio(cp1.getPreco_unit());

        produto2.setQtde_estoque(produto2.getQtde_estoque() + cp2.getQuantidade());
        produto2.setValor_ultima_compra(cp2.getPreco_unit());
        produto2.setPreco_medio(cp2.getPreco_unit());

        compraController.salvar(compra1);

        // venda
        Venda venda1 = new Venda(1, LocalDate.now(), 60000.00, cliente1);

        ProdutoVenda vp1 = new ProdutoVenda(1, venda1, produto1, 2, 3000.00);
        if (produtoController.verificaEstoqueExistente(vp1.getProduto())) {
            System.out.println("Produto 1 ok");
        } else {
            System.out.println("Produto 1 sem estoque");
        }

        ProdutoVenda vp2 = new ProdutoVenda(2, venda1, produto2, 1, 2500.00);
        if (produtoController.verificaEstoqueExistente(vp2.getProduto())) {
            System.out.println("Produto 2 ok");
        } else {
            System.out.println("Produto 2 sem estoque");
        }

        List<ProdutoVenda> vendaProdutos = new ArrayList<>();
        vendaProdutos.add(vp1);
        vendaProdutos.add(vp2);

        venda1.setprodutos(vendaProdutos);

        // atualiza produto (venda)
        produto1.setQtde_estoque(produto1.getQtde_estoque() - vp1.getQuantidade());
        produto1.setValor_ultima_venda(vp1.getPreco_unit());

        produto2.setQtde_estoque(produto2.getQtde_estoque() - vp2.getQuantidade());
        produto2.setValor_ultima_venda(vp2.getPreco_unit());

        vendaController.salvar(venda1);
    }
}
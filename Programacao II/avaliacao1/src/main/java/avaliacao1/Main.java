package avaliacao1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import avaliacao1.controller.*;
import avaliacao1.model.*;

public class Main {
    public static void main(String[] args) {

        // controllers
        CategoriaController categoriaController = new CategoriaController();
        ClienteController clienteController = new ClienteController();
        FornecedorController fornecedorController = new FornecedorController();
        ProdutoController produtoController = new ProdutoController();
        FornecedorProdutoController fornecedorProdutoController = new FornecedorProdutoController();
        CompraController compraController = new CompraController();
        VendaController vendaController = new VendaController();

        // categoria
        Categoria categoria1 = new Categoria(1, "Eletrônicos");
        System.out.println("Categoria: " + categoriaController.salvar(categoria1));

        // cliente
        Cliente cliente1 = new Cliente(1, "João", "123", "sim", "Rua 1", "99999");
        System.out.println("Cliente: " + clienteController.salvar(cliente1));

        // fornecedor
        Fornecedor fornecedor1 = new Fornecedor(1, "Fornecedor A", "111", "11/1111");
        System.out.println("Fornecedor:" + fornecedorController.salvar(fornecedor1));


        // produto
        Produto produto1 = new Produto(1, "Notebook", 0.0, 0, categoria1, 0.0, 0.0);
        System.out.println("Produto:" + produtoController.salvar(produto1));

        // fornecedor produto
        FornecedorProduto fp1 = new FornecedorProduto(1, fornecedor1, produto1);
        System.out.println("FornecedorProduto:" + fornecedorProdutoController.salvar(fp1));

        // compra (aumenta estoque)
        Compra compra1 = new Compra(1, LocalDate.now(), 0.0, fornecedor1);

        CompraProduto cp1 = new CompraProduto(1, produto1, compra1, 7, 3000.00);

        List<CompraProduto> listaCompra = new ArrayList<>();
        listaCompra.add(cp1);

        compra1.setProdutos(listaCompra);

        System.out.println("Compra: " + compraController.salvar(compra1));

        //         // compra (aumenta estoque) teste
        // Compra compra2 = new Compra(2, LocalDate.now(), 0.0, fornecedor1);

        // CompraProduto cp2 = new CompraProduto(2, produto1, compra2, 7, 3500.00);

        // listaCompra.add(cp2);

        // compra2.setProdutos(listaCompra);

        // System.out.println("Compra2: " + compraController.salvar(compra2));

        // venda 1
        Venda venda1 = new Venda(1, LocalDate.now(), 6000.0, cliente1);

        VendaProduto vp1 = new VendaProduto(1, venda1, produto1, 2, 3000.00);

        List<VendaProduto> listaVenda1 = new ArrayList<>();
        listaVenda1.add(vp1);

        venda1.setprodutos(listaVenda1);

        System.out.println("Venda 1: " + vendaController.salvar(venda1));

        // venda 2
        Venda venda2 = new Venda(2, LocalDate.now(), 6000.0, cliente1);

        VendaProduto vp2 = new VendaProduto(2, venda2, produto1, 2, 3000.00);

        List<VendaProduto> listaVenda2 = new ArrayList<>();
        listaVenda2.add(vp2);

        venda2.setprodutos(listaVenda2);

        System.out.println("Venda 2: " + vendaController.salvar(venda2));

        // venda 3
        Venda venda3 = new Venda(3, LocalDate.now(), 6000.0, cliente1);

        VendaProduto vp3 = new VendaProduto(3, venda3, produto1, 3, 3000.00);

        List<VendaProduto> listaVenda3 = new ArrayList<>();
        listaVenda3.add(vp3);

        venda3.setprodutos(listaVenda3);

        System.out.println("Venda 3: " + vendaController.salvar(venda3));

        // venda 4 (deve bloquear)
        Venda venda4 = new Venda(4, LocalDate.now(), 6000.0, cliente1);

        VendaProduto vp4 = new VendaProduto(4, venda4, produto1, 1, 3000.00);

        List<VendaProduto> listaVenda4 = new ArrayList<>();
        listaVenda4.add(vp4);

        venda4.setprodutos(listaVenda4);

        System.out.println("Venda 4: " + vendaController.salvar(venda4));
    }
}
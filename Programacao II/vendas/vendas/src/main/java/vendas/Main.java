package vendas;

import java.util.Scanner;

import vendas.view.ClienteView;
import vendas.view.ProdutoView;
import vendas.view.FornecedorView;
import vendas.view.VendaView;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClienteView clienteView = new ClienteView();
        ProdutoView produtoView = new ProdutoView();
        FornecedorView fornecedorView = new FornecedorView();
        VendaView vendaView = new VendaView();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE VENDAS ===");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Fornecedores");
            System.out.println("4 - Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: clienteView.menu(); break;
                case 2: produtoView.menu(); break;
                case 3: fornecedorView.menu(); break;
                case 4: vendaView.menu(); break;
                case 0: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
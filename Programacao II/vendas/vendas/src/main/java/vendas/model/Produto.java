package vendas.model;

import java.util.List;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private double qtde_estoque;
    private Categoria categoria;
    private List<Fornecedor> fornecedores;

    public Produto(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getQtde_estoque() {
        return qtde_estoque;
    }
    public void setQtde_estoque(double qtde_estoque) {
        this.qtde_estoque = qtde_estoque;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
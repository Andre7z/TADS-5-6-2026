package vendas.model;
import java.util.List;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private double qtde_estoque;
    private Categoria Categoria;
    private List<FornecedorProduto> produtos;

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
        return Categoria;
    }
    public void setCategoria(Categoria categoria) {
        Categoria = categoria;
    }
    public List<FornecedorProduto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<FornecedorProduto> produtos) {
        this.produtos = produtos;
    }
    public Produto(int id, String nome, double preco, double qtde_estoque, vendas.model.Categoria categoria,
            List<FornecedorProduto> produtos) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtde_estoque = qtde_estoque;
        this.Categoria = categoria;
        this.produtos = produtos;
    }
    
}

package avaliacao1.model;

public class CompraProduto {
    private int id;
    private Produto produto;
    private Compra compra;
    private int quantidade;
    private double preco_unit;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getPreco_unit() {
        return preco_unit;
    }
    public void setPreco_unit(double preco_unit) {
        this.preco_unit = preco_unit;
    }

    public CompraProduto(){}
    public CompraProduto(int id, Produto produto, Compra compra, int quantidade, double preco_unit) {
        this.id = id;
        this.produto = produto;
        this.compra = compra;
        this.quantidade = quantidade;
        this.preco_unit = preco_unit;
    }

}

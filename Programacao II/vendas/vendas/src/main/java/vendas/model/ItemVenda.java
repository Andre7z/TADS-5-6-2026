package vendas.model;

public class ItemVenda {
    private int id;
    private Venda venda;
    private Produto produto;
    private int quantidade;
    private double preco_unit;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public ItemVenda(){}

    public ItemVenda(int id, Venda venda, Produto produto, int quantidade, double preco_unit) {
        this.id = id;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco_unit = preco_unit;
    }

    
}
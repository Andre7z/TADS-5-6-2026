package avaliacao1.model;
import java.time.LocalDate;
import java.util.List;

public class Venda {
    
    private int id;
    private LocalDate data_venda;
    private double valor_total;
    private List<ProdutoVenda> produtos;
    private Cliente cliente;


    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getData_venda() {
        return data_venda;
    }
    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }
    public double getValor_total() {
        return valor_total;
    }
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }
    public List<ProdutoVenda> getprodutos() {
        return produtos;
    }
    public void setprodutos(List<ProdutoVenda> produtos) {
        this.produtos = produtos;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Venda() {}
    public Venda(int id, LocalDate data_venda, double valor_total, Cliente cliente) {
        this.id = id;
        this.data_venda = data_venda;
        this.valor_total = valor_total;
        this.cliente = cliente;
    }
    

}

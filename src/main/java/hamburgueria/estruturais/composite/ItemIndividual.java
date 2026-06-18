package hamburgueria.estruturais.composite;

public class ItemIndividual extends ItemCardapio {

    private int preco;

    public ItemIndividual(String descricao, int preco) {
        super(descricao);
        this.preco = preco;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getDetalhes() {
        return "Item: " + this.getDescricao() + " - preço: R$ " + this.preco + "\n";
    }
}
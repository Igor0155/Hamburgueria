package hamburgueria.estruturais.composite;

public class Pedido {

    private ItemCardapio itens;

    public void setItens(ItemCardapio itens) {
        this.itens = itens;
    }

    public ItemCardapio getItensObjeto() {
        return this.itens;
    }

    public String getItens() {
        if (this.itens == null) {
            throw new NullPointerException("Pedido sem itens");
        }
        return this.itens.getDetalhes();
    }
}
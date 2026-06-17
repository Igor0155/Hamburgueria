package hamburgueria;

public abstract class HamburguerDecorator implements ComponenteCardapio {
    protected ComponenteCardapio lanche;

    public HamburguerDecorator(ComponenteCardapio lanche) {
        this.lanche = lanche;
    }

    public abstract String getAdicionalDescricao();

    public abstract float getAdicionalPreco();

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + " + getAdicionalDescricao();
    }

    @Override
    public float getPreco() {
        return lanche.getPreco() + getAdicionalPreco();
    }
}
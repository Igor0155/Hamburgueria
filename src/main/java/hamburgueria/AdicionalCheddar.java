package hamburgueria;

public class AdicionalCheddar extends HamburguerDecorator {
    public AdicionalCheddar(ComponenteCardapio lanche) {
        super(lanche);
    }

    @Override
    public String getAdicionalDescricao() {
        return "Cheddar Cremoso";
    }

    @Override
    public float getAdicionalPreco() {
        return 5.0f;
    }
}
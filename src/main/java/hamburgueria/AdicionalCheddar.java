package hamburgueria;

public class AdicionalCheddar extends HamburguerDecorator {
    public AdicionalCheddar(ComponenteCardapio lanche) {
        super(lanche);
    }

    public String getAdicionalDescricao() {
        return "Cheddar Cremoso";
    }

    public float getAdicionalPreco() {
        return 5.0f;
    }
}
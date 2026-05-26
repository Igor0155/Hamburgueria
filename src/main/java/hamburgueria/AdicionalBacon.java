package hamburgueria;

class AdicionalBacon extends HamburguerDecorator {
    public AdicionalBacon(ComponenteCardapio lanche) {
        super(lanche);
    }

    public String getAdicionalDescricao() {
        return "Bacon Crocante";
    }

    public float getAdicionalPreco() {
        return 7.0f;
    }
}
package hamburgueria;

class AdicionalBacon extends HamburguerDecorator {
    public AdicionalBacon(ComponenteCardapio lanche) {
        super(lanche);
    }

    @Override
    public String getAdicionalDescricao() {
        return "Bacon Crocante";
    }

    @Override
    public float getAdicionalPreco() {
        return 7.0f;
    }
}
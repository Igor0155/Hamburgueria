package hamburgueria;

class AdicionalOnionRings extends HamburguerDecorator {
    public AdicionalOnionRings(ComponenteCardapio lanche) {
        super(lanche);
    }

    @Override
    public String getAdicionalDescricao() {
        return "Onion Rings";
    }

    @Override
    public float getAdicionalPreco() {
        return 6.0f;
    }
}
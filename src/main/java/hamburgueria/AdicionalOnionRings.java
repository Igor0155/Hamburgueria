package hamburgueria;

class AdicionalOnionRings extends HamburguerDecorator {
    public AdicionalOnionRings(ComponenteCardapio lanche) {
        super(lanche);
    }

    public String getAdicionalDescricao() {
        return "Onion Rings";
    }

    public float getAdicionalPreco() {
        return 6.0f;
    }
}
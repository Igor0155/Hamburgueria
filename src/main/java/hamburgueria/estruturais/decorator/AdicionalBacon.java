package hamburgueria.estruturais.decorator;

public class AdicionalBacon extends LancheDecorator {

    public AdicionalBacon(Lanche lanche) {
        super(lanche);
    }

    public float getPrecoAdicional() {
        return 5.0f; // R$ 5.00
    }

    public String getNomeAdicional() {
        return "Bacon";
    }
}
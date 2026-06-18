package hamburgueria.estruturais.decorator;

public class AdicionalCheddar extends LancheDecorator {

    public AdicionalCheddar(Lanche lanche) {
        super(lanche);
    }

    public float getPrecoAdicional() {
        return 4.0f; // R$ 4.00
    }

    public String getNomeAdicional() {
        return "Cheddar";
    }
}
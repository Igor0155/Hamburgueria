package hamburgueria.estruturais.decorator;

public class AdicionalCebolaCaramelizada extends LancheDecorator {

    public AdicionalCebolaCaramelizada(Lanche lanche) {
        super(lanche);
    }

    public float getPrecoAdicional() {
        return 3.0f; // R$ 3.00
    }

    public String getNomeAdicional() {
        return "Cebola Caramelizada";
    }
}
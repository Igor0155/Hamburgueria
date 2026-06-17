package hamburgueria.criacionais;

public class CopoSeladoDelivery implements CopoBebida {
    @Override
    public String servir() {
        return "Bebida no Copo de Papel Selado";
    }
}
package hamburgueria.comportamentais;

public class Gerente extends TratadorReclamacao {
    @Override
    protected boolean temAutorizacao(float valor) {
        return valor <= 50.0f;
    }

    @Override
    protected String getCargo() {
        return "Gerente";
    }
}
package hamburgueria.comportamentais;

public class Atendente extends TratadorReclamacao {

    @Override
    protected boolean temAutorizacao(float valor) {
        return valor <= 15.0f;
    }

    @Override
    protected String getCargo() {
        return "Atendente";
    }
}
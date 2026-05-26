package hamburgueria.comportamentais;

public class Atendente extends TratadorReclamacao {
    protected boolean temAutorizacao(float valor) {
        return valor <= 15.0f;
    }

    protected String getCargo() {
        return "Atendente";
    }
}
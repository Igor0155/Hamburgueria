package hamburgueria.comportamentais;

public class Gerente extends TratadorReclamacao {
    protected boolean temAutorizacao(float valor) {
        return valor <= 50.0f;
    }

    protected String getCargo() {
        return "Gerente";
    }
}
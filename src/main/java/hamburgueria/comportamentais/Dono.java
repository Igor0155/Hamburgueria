package hamburgueria.comportamentais;

public class Dono extends TratadorReclamacao {
    protected boolean temAutorizacao(float valor) {
        return valor <= 500.0f;
    } // Dono aprova até 500

    protected String getCargo() {
        return "Dono da Hamburgueria";
    }
}
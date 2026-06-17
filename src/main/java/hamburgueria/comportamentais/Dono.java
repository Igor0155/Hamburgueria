package hamburgueria.comportamentais;

public class Dono extends TratadorReclamacao {

    @Override
    protected boolean temAutorizacao(float valor) {
        return valor <= 500.0f;
    } // Dono aprova até 500

    @Override
    protected String getCargo() {
        return "Dono da Hamburgueria";
    }
}
package hamburgueria.comportamentais;

public abstract class TratadorReclamacao {
    private TratadorReclamacao superior;

    public void setSuperior(TratadorReclamacao superior) {
        this.superior = superior;
    }

    public String processarReclamacao(float valorDescontoPedido) {
        if (temAutorizacao(valorDescontoPedido)) {
            return getCargo() + " aprovou o desconto de R$ " + valorDescontoPedido;
        } else if (superior != null) {
            return superior.processarReclamacao(valorDescontoPedido);
        } else {
            return "Desconto negado. Nenhum cargo tem autorização para este valor.";
        }
    }

    protected abstract boolean temAutorizacao(float valor);

    protected abstract String getCargo();
}
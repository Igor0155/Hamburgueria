package hamburgueria.comportamentais;

public abstract class ProcessamentoCaixa {

    public final String fecharCaixa() {
        return contarValores() + " -> Caixa Fechado no Sistema -> " + emitirComprovante();
    }

    protected abstract String contarValores();

    protected abstract String emitirComprovante();
}
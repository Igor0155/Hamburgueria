package hamburgueria.comportamentais.chainofresponsibility;

public class TipoSolicitacaoDuvida implements TipoSolicitacao {
    private static TipoSolicitacaoDuvida instancia = new TipoSolicitacaoDuvida();

    private TipoSolicitacaoDuvida() {
    }

    public static TipoSolicitacaoDuvida getInstancia() {
        return instancia;
    }
}
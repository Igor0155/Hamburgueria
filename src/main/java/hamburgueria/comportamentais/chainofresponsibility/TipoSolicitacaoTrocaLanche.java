package hamburgueria.comportamentais.chainofresponsibility;

public class TipoSolicitacaoTrocaLanche implements TipoSolicitacao {
    private static TipoSolicitacaoTrocaLanche instancia = new TipoSolicitacaoTrocaLanche();

    private TipoSolicitacaoTrocaLanche() {
    }

    public static TipoSolicitacaoTrocaLanche getInstancia() {
        return instancia;
    }
}
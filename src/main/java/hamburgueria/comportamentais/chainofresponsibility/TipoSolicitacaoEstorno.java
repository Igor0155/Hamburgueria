package hamburgueria.comportamentais.chainofresponsibility;

public class TipoSolicitacaoEstorno implements TipoSolicitacao {
    private static TipoSolicitacaoEstorno instancia = new TipoSolicitacaoEstorno();

    private TipoSolicitacaoEstorno() {
    }

    public static TipoSolicitacaoEstorno getInstancia() {
        return instancia;
    }
}
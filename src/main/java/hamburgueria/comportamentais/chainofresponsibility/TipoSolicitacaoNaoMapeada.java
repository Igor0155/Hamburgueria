package hamburgueria.comportamentais.chainofresponsibility;

public class TipoSolicitacaoNaoMapeada implements TipoSolicitacao {
    private static TipoSolicitacaoNaoMapeada instancia = new TipoSolicitacaoNaoMapeada();

    private TipoSolicitacaoNaoMapeada() {
    }

    public static TipoSolicitacaoNaoMapeada getInstancia() {
        return instancia;
    }
}
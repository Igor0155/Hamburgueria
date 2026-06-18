package hamburgueria.comportamentais.chainofresponsibility;

public class TipoSolicitacaoJuridica implements TipoSolicitacao {
    private static TipoSolicitacaoJuridica instancia = new TipoSolicitacaoJuridica();

    private TipoSolicitacaoJuridica() {
    }

    public static TipoSolicitacaoJuridica getInstancia() {
        return instancia;
    }
}
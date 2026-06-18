package hamburgueria.comportamentais.chainofresponsibility;

import java.util.ArrayList;

public abstract class MembroEquipe {

    protected ArrayList<TipoSolicitacao> listaSolicitacoes = new ArrayList<>();
    private MembroEquipe superior;

    public MembroEquipe getSuperior() {
        return superior;
    }

    public void setSuperior(MembroEquipe superior) {
        this.superior = superior;
    }

    public abstract String getDescricaoCargo();

    public String processarSolicitacao(SolicitacaoSuporte solicitacao) {
        if (listaSolicitacoes.contains(solicitacao.getTipoSolicitacao())) {
            return getDescricaoCargo(); // Resolvido por este nível
        } else {
            if (superior != null) {
                return superior.processarSolicitacao(solicitacao); // Repassa para o chefe
            } else {
                return "Sem resolução"; // Chegou no topo e ninguém resolveu
            }
        }
    }
}
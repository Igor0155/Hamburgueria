package hamburgueria.comportamentais.chainofresponsibility;

public class AtendenteCaixa extends MembroEquipe {
    public AtendenteCaixa(MembroEquipe superior) {
        listaSolicitacoes.add(TipoSolicitacaoDuvida.getInstancia());
        setSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Atendente de Caixa";
    }
}
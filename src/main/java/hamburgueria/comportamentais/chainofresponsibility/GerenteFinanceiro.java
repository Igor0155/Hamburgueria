package hamburgueria.comportamentais.chainofresponsibility;

public class GerenteFinanceiro extends MembroEquipe {
    public GerenteFinanceiro(MembroEquipe superior) {
        listaSolicitacoes.add(TipoSolicitacaoEstorno.getInstancia());
        setSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Gerente Financeiro";
    }
}
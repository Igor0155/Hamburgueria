package hamburgueria.comportamentais.chainofresponsibility;

public class ChefeCozinha extends MembroEquipe {
    public ChefeCozinha(MembroEquipe superior) {
        listaSolicitacoes.add(TipoSolicitacaoTrocaLanche.getInstancia());
        setSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Chefe de Cozinha";
    }
}
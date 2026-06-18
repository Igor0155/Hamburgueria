package hamburgueria.comportamentais.chainofresponsibility;

public class SetorJuridico extends MembroEquipe {
    public SetorJuridico(MembroEquipe superior) {
        listaSolicitacoes.add(TipoSolicitacaoJuridica.getInstancia());
        setSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Setor Jurídico";
    }
}
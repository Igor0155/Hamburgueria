package hamburgueria.criacionais.factorymethod;

public class PagamentoValeRefeicao implements IPagamento {

    @Override
    public String processar() {
        return "Pagamento via Vale Refeição efetivado";
    }

    @Override
    public String cancelar() {
        return "Pagamento via Vale Refeição cancelado";
    }
}
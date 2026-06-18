package hamburgueria.criacionais.factorymethod;

public class PagamentoCartaoCredito implements IPagamento {
    @Override
    public String processar() {
        return "Pagamento via Cartão de Crédito efetivado";
    }

    @Override
    public String cancelar() {
        return "Estorno no Cartão de Crédito efetivado";
    }
}
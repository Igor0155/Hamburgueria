package hamburgueria.criacionais;

public class PagamentoCartao implements Pagamento {

    @Override
    public String processarPagamento(float valor) {
        return "Pagamento de R$ " + valor + " processado via Cartão de Crédito.";
    }
}
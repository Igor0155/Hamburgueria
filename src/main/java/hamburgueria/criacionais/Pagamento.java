package hamburgueria.criacionais;

public interface Pagamento {
    String processarPagamento(float valor);
}

class PagamentoPix implements Pagamento {
    public String processarPagamento(float valor) {
        return "Pagamento de R$ " + valor + " processado via PIX.";
    }
}

class PagamentoCartao implements Pagamento {
    public String processarPagamento(float valor) {
        return "Pagamento de R$ " + valor + " processado via Cartão de Crédito.";
    }
}
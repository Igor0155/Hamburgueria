package hamburgueria.criacionais;

public class PagamentoFactory {
    public static Pagamento criarPagamento(String tipo) {
        if (tipo.equalsIgnoreCase("PIX")) {
            return new PagamentoPix();
        } else if (tipo.equalsIgnoreCase("CARTAO")) {
            return new PagamentoCartao();
        }
        throw new IllegalArgumentException("Tipo de pagamento não suportado.");
    }
}
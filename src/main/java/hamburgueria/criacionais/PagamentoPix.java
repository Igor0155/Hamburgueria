package hamburgueria.criacionais;

public class PagamentoPix implements Pagamento {

    @Override
    public String processarPagamento(float valor) {
        return "Pagamento de R$ " + valor + " processado via PIX.";
    }
}
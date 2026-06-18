package hamburgueria.criacionais.factorymethod;

public class PagamentoPix implements IPagamento {

    @Override
    public String processar() {
        return "Pagamento via PIX efetivado";
    }

    @Override
    public String cancelar() {
        return "Pagamento via PIX cancelado";
    }
}
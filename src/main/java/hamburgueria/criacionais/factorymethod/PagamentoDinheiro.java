package hamburgueria.criacionais.factorymethod;

public class PagamentoDinheiro implements IPagamento {

    @Override
    public String processar() {
        return "Pagamento em Dinheiro recebido no caixa";
    }

    @Override
    public String cancelar() {
        return "Dinheiro devolvido ao cliente";
    }
}
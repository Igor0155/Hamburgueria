package hamburgueria.estruturais.bridge;

import hamburgueria.criacionais.builder.PedidoCliente;

public class AtendimentoDelivery extends ModalidadeAtendimento {

    private float taxaFreteFixo = 15.0f;

    public AtendimentoDelivery(PedidoCliente pedido) {
        super(pedido);
    }

    @Override
    public float calcularValorFinal() {
        // O frete é somado ANTES do desconto de fidelidade
        float valorBase = this.pedido.getLanchePrincipal().getPreco();
        return (valorBase + taxaFreteFixo) * (1 - this.fidelidade.getPercentualDesconto());
    }
}
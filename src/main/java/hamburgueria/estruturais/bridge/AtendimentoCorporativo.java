package hamburgueria.estruturais.bridge;

import hamburgueria.criacionais.builder.PedidoCliente;

public class AtendimentoCorporativo extends ModalidadeAtendimento {

    public AtendimentoCorporativo(PedidoCliente pedido) {
        super(pedido);
    }

    @Override
    public float calcularValorFinal() {
        // Contratos B2B já têm 20% de desconto na base (0.8f), mais o nível de
        // fidelidade
        float valorBase = this.pedido.getLanchePrincipal().getPreco() * 0.80f;
        return valorBase * (1 - this.fidelidade.getPercentualDesconto());
    }
}
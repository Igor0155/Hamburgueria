package hamburgueria.estruturais.bridge;

import hamburgueria.criacionais.builder.PedidoCliente;

public class AtendimentoBalcao extends ModalidadeAtendimento {

    public AtendimentoBalcao(PedidoCliente pedido) {
        super(pedido);
    }

    @Override
    public float calcularValorFinal() {
        // Pega o valor real do lanche montado no Decorator e aplica o desconto
        float valorBase = this.pedido.getLanchePrincipal().getPreco();
        return valorBase * (1 - this.fidelidade.getPercentualDesconto());
    }
}
package hamburgueria.estruturais.bridge;

import hamburgueria.criacionais.builder.PedidoCliente;

public abstract class ModalidadeAtendimento {

    protected NivelFidelidade fidelidade;
    protected PedidoCliente pedido; // INTEGRAÇÃO COM O BUILDER E DECORATOR

    public ModalidadeAtendimento(PedidoCliente pedido) {
        if (pedido == null || pedido.getLanchePrincipal() == null) {
            throw new IllegalArgumentException("Pedido ou lanche principal não pode ser nulo");
        }
        this.pedido = pedido;
    }

    public void setFidelidade(NivelFidelidade fidelidade) {
        this.fidelidade = fidelidade;
    }

    public void setPedido(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    // Método que as filhas deverão implementar usando os dados do PedidoCliente
    public abstract float calcularValorFinal();
}
package hamburgueria.comportamentais.templatemethod;

import hamburgueria.criacionais.builder.PedidoCliente;

public abstract class ProcessadorFechamento {

    protected PedidoCliente pedido;

    public PedidoCliente getPedido() {
        return pedido;
    }

    public void setPedido(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public float calcularTotalBase() {
        if (this.pedido == null || this.pedido.getLanchePrincipal() == null) {
            return 0.0f;
        }
        return this.pedido.getLanchePrincipal().getPreco();
    }

    public abstract String verificarStatusLiberacao();

    public String getTipoCanal() {
        return "Canal Padrão";
    }

    public String emitirComprovante() {
        return getTipoCanal() + "{" +
                "pedido=" + this.pedido.getNumeroPedido() +
                ", cliente='" + this.pedido.getNomeCliente() + '\'' +
                ", status=" + this.verificarStatusLiberacao() +
                '}';
    }
}
package hamburgueria.comportamentais.state;

import hamburgueria.criacionais.builder.PedidoCliente;

public abstract class PedidoEstado {

    public abstract String getNomeEstado();

    public boolean iniciarPreparo(PedidoCliente pedido) {
        return false;
    }

    public boolean marcarPronto(PedidoCliente pedido) {
        return false;
    }

    public boolean enviarParaEntrega(PedidoCliente pedido) {
        return false;
    }

    public boolean entregar(PedidoCliente pedido) {
        return false;
    }

    public boolean cancelar(PedidoCliente pedido) {
        return false;
    }
}
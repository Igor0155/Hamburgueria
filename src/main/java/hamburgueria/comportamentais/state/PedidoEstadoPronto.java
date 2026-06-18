package hamburgueria.comportamentais.state;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PedidoEstadoPronto extends PedidoEstado {

    private PedidoEstadoPronto() {
    };

    private static PedidoEstadoPronto instance = new PedidoEstadoPronto();

    public static PedidoEstadoPronto getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Pronto";
    }

    public boolean enviarParaEntrega(PedidoCliente pedido) {

        if (pedido.despacharPedido()) {
            pedido.setEstado(PedidoEstadoEmRota.getInstance());
            return true;
        }
        return false; // Barrado pela logística/financeiro!
    }

    public boolean entregar(PedidoCliente pedido) {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        return true;
    }
}
package hamburgueria.comportamentais.state;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PedidoEstadoEmRota extends PedidoEstado {

    private PedidoEstadoEmRota() {
    };

    private static PedidoEstadoEmRota instance = new PedidoEstadoEmRota();

    public static PedidoEstadoEmRota getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Em Rota";
    }

    public boolean entregar(PedidoCliente pedido) {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        return true;
    }
}
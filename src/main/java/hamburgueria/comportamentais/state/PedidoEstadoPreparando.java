package hamburgueria.comportamentais.state;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PedidoEstadoPreparando extends PedidoEstado {

    private PedidoEstadoPreparando() {
    };

    private static PedidoEstadoPreparando instance = new PedidoEstadoPreparando();

    public static PedidoEstadoPreparando getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Preparando";
    }

    public boolean marcarPronto(PedidoCliente pedido) {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        return true;
    }

    public boolean cancelar(PedidoCliente pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}
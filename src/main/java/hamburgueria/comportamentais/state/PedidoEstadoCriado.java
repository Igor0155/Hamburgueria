package hamburgueria.comportamentais.state;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PedidoEstadoCriado extends PedidoEstado {

    private PedidoEstadoCriado() {
    };

    private static PedidoEstadoCriado instance = new PedidoEstadoCriado();

    public static PedidoEstadoCriado getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Criado";
    }

    public boolean iniciarPreparo(PedidoCliente pedido) {
        pedido.setEstado(PedidoEstadoPreparando.getInstance());
        return true;
    }

    public boolean cancelar(PedidoCliente pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}
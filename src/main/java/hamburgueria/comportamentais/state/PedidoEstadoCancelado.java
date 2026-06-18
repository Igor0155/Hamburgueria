package hamburgueria.comportamentais.state;

public class PedidoEstadoCancelado extends PedidoEstado {

    private PedidoEstadoCancelado() {
    };

    private static PedidoEstadoCancelado instance = new PedidoEstadoCancelado();

    public static PedidoEstadoCancelado getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Cancelado";
    }
}
package hamburgueria.comportamentais;

public class EstadoEntregue implements EstadoPedido {
    public String getNomeEstado() {
        return "Entregue";
    }

    public boolean preparar(PedidoFluxo pedido) {
        return false;
    }

    public boolean finalizar(PedidoFluxo pedido) {
        return false;
    }

    public boolean entregar(PedidoFluxo pedido) {
        return false;
    }
}
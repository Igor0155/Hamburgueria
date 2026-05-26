package hamburgueria.comportamentais;

public class EstadoPreparando implements EstadoPedido {
    public String getNomeEstado() {
        return "Preparando";
    }

    public boolean preparar(PedidoFluxo pedido) {
        return false;
    }

    public boolean finalizar(PedidoFluxo pedido) {
        pedido.setEstado(new EstadoPronto());
        return true;
    }

    public boolean entregar(PedidoFluxo pedido) {
        return false;
    }
}
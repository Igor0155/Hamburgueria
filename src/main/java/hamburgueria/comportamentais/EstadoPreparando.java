package hamburgueria.comportamentais;

public class EstadoPreparando implements EstadoPedido {

    @Override
    public String getNomeEstado() {
        return "Preparando";
    }

    @Override
    public boolean preparar(PedidoFluxo pedido) {
        return false;
    }

    @Override
    public boolean finalizar(PedidoFluxo pedido) {
        pedido.setEstado(new EstadoPronto());
        return true;
    }

    @Override
    public boolean entregar(PedidoFluxo pedido) {
        return false;
    }
}
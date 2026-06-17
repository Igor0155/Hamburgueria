package hamburgueria.comportamentais;

public class EstadoEntregue implements EstadoPedido {

    @Override
    public String getNomeEstado() {
        return "Entregue";
    }

    @Override
    public boolean preparar(PedidoFluxo pedido) {
        return false;
    }

    @Override
    public boolean finalizar(PedidoFluxo pedido) {
        return false;
    }

    @Override
    public boolean entregar(PedidoFluxo pedido) {
        return false;
    }
}
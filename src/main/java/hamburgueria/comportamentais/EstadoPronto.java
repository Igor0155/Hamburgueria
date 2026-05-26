package hamburgueria.comportamentais;

public class EstadoPronto implements EstadoPedido {
    public String getNomeEstado() {
        return "Pronto";
    }

    public boolean preparar(PedidoFluxo pedido) {
        return false;
    }

    public boolean finalizar(PedidoFluxo pedido) {
        return false;
    }

    public boolean entregar(PedidoFluxo pedido) {
        pedido.setEstado(new EstadoEntregue());
        return true;
    }
}
package hamburgueria.comportamentais;

public interface EstadoPedido {
    String getNomeEstado();

    boolean preparar(PedidoFluxo pedido);

    boolean finalizar(PedidoFluxo pedido);

    boolean entregar(PedidoFluxo pedido);
}
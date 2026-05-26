package hamburgueria.comportamentais;

public class EstadoNovo implements EstadoPedido {
    public String getNomeEstado() {
        return "Novo";
    }

    public boolean preparar(PedidoFluxo pedido) {
        pedido.setEstado(new EstadoPreparando());
        return true;
    }

    public boolean finalizar(PedidoFluxo pedido) {
        return false;
    }

    public boolean entregar(PedidoFluxo pedido) {
        return false;
    }
}
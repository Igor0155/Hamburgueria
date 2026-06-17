package hamburgueria.comportamentais;

public class EstadoNovo implements EstadoPedido {

    @Override
    public String getNomeEstado() {
        return "Novo";
    }

    @Override
    public boolean preparar(PedidoFluxo pedido) {
        pedido.setEstado(new EstadoPreparando());
        return true;
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
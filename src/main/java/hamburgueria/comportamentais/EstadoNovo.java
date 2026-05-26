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
    } // Não pode ir de Novo direto pra Pronto

    public boolean entregar(PedidoFluxo pedido) {
        return false;
    }
}
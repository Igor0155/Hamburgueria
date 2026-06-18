package hamburgueria.estruturais.facade;

public class PedidoFacade {

    public static boolean verificarPendenciasLiberacao(Pedido pedido) {
        if (Cozinha.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false;
        }
        if (Estoque.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false;
        }
        if (Financeiro.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false;
        }
        if (Logistica.getInstancia().verificarPedidoComPendencia(pedido)) {
            return false;
        }
        return true;
    }
}
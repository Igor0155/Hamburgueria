package hamburgueria.estruturais.facade;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PedidoFacade {

    public static boolean verificarPendenciasLiberacao(PedidoCliente pedido) {
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
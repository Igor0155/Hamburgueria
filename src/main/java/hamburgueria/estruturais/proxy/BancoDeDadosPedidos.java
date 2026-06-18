package hamburgueria.estruturais.proxy;

import java.util.HashMap;
import java.util.Map;

import hamburgueria.criacionais.builder.PedidoCliente;

public class BancoDeDadosPedidos {
    private static Map<Integer, PedidoCliente> pedidos = new HashMap<>();

    public static PedidoCliente getPedido(Integer numeroPedido) {
        return pedidos.get(numeroPedido);
    }

    public static void addPedido(PedidoCliente pedido) {
        pedidos.put(pedido.getNumeroPedido(), pedido);
    }

    public static void limparCache() {
        pedidos.clear();
    }
}
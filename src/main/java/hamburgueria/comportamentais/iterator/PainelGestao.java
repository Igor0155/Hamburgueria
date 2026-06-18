package hamburgueria.comportamentais.iterator;

import java.util.Iterator;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PainelGestao {

    public static Integer contarTotalPedidos(FilaPedidos fila) {
        int quantidade = 0;
        for (Iterator<PedidoCliente> it = fila.iterator(); it.hasNext();) {
            quantidade++;
            it.next();
        }
        return quantidade;
    }

    public static Integer contarPedidosEntregues(FilaPedidos fila) {
        int quantidade = 0;
        for (PedidoCliente pedido : fila) {
            // INTEGRAÇÃO: O Iterator acessa a máquina de Estados (Padrão State)
            if (pedido.getNomeEstado().equals("Entregue")) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public static Integer contarPedidosCancelados(FilaPedidos fila) {
        int quantidade = 0;
        for (PedidoCliente pedido : fila) {
            // INTEGRAÇÃO: O Iterator acessa a máquina de Estados (Padrão State)
            if (pedido.getNomeEstado().equals("Cancelado")) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public static Integer contarPedidosPremium(FilaPedidos fila) {
        int quantidade = 0;
        for (PedidoCliente pedido : fila) {
            // INTEGRAÇÃO: O Iterator acessa os objetos em cascata (Padrão Decorator)
            if (pedido.getLanchePrincipal().getPreco() >= 40.0f) {
                quantidade++;
            }
        }
        return quantidade;
    }
}
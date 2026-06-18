package hamburgueria.estruturais.facade;

import java.util.ArrayList;
import java.util.List;

public abstract class Departamento {

    private List<Pedido> pedidosComPendencia = new ArrayList<Pedido>();

    public void addPedidoPendente(Pedido pedido) {
        this.pedidosComPendencia.add(pedido);
    }

    public void removerPedidoPendente(Pedido pedido) {
        this.pedidosComPendencia.remove(pedido);
    }

    public boolean verificarPedidoComPendencia(Pedido pedido) {
        return this.pedidosComPendencia.contains(pedido);
    }
}
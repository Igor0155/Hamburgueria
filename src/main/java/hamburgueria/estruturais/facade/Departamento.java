package hamburgueria.estruturais.facade;

import java.util.ArrayList;
import java.util.List;

import hamburgueria.criacionais.builder.PedidoCliente;

public abstract class Departamento {
    private List<PedidoCliente> pedidosComPendencia = new ArrayList<>();

    public void addPedidoPendente(PedidoCliente pedido) {
        this.pedidosComPendencia.add(pedido);
    }

    public void removerPedidoPendente(PedidoCliente pedido) {
        this.pedidosComPendencia.remove(pedido);
    }

    public boolean verificarPedidoComPendencia(PedidoCliente pedido) {
        return this.pedidosComPendencia.contains(pedido);
    }
}
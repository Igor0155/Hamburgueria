package hamburgueria.comportamentais.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import hamburgueria.criacionais.builder.PedidoCliente;

public class FilaPedidos implements Iterable<PedidoCliente> {

    private List<PedidoCliente> pedidos;

    public FilaPedidos(PedidoCliente... pedidos) {
        this.pedidos = Arrays.asList(pedidos);
    }

    @Override
    public Iterator<PedidoCliente> iterator() {
        return pedidos.iterator();
    }
}
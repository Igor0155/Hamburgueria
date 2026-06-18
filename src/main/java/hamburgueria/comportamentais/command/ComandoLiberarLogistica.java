package hamburgueria.comportamentais.command;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.estruturais.facade.Logistica;

public class ComandoLiberarLogistica implements ComandoPedido {

    private PedidoCliente pedido;

    public ComandoLiberarLogistica(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public void executar() {
        Logistica.getInstancia().removerPedidoPendente(this.pedido);
    }

    public void desfazer() {
        Logistica.getInstancia().addPedidoPendente(this.pedido);
    }
}
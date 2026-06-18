package hamburgueria.comportamentais.command;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.estruturais.facade.Logistica;

public class ComandoReterLogistica implements ComandoPedido {

    private PedidoCliente pedido;

    public ComandoReterLogistica(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public void executar() {
        Logistica.getInstancia().addPedidoPendente(this.pedido);
    }

    public void desfazer() {
        Logistica.getInstancia().removerPedidoPendente(this.pedido);
    }
}
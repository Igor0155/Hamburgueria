package hamburgueria.comportamentais.command;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.estruturais.facade.Financeiro;

public class ComandoReterFinanceiro implements ComandoPedido {

    private PedidoCliente pedido;

    public ComandoReterFinanceiro(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public void executar() {
        Financeiro.getInstancia().addPedidoPendente(this.pedido);
    }

    public void desfazer() {
        Financeiro.getInstancia().removerPedidoPendente(this.pedido);
    }
}
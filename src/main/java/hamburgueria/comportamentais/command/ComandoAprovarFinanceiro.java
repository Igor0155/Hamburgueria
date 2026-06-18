package hamburgueria.comportamentais.command;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.estruturais.facade.Financeiro;

public class ComandoAprovarFinanceiro implements ComandoPedido {

    private PedidoCliente pedido;

    public ComandoAprovarFinanceiro(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public void executar() {
        Financeiro.getInstancia().removerPedidoPendente(this.pedido);
    }

    public void desfazer() {
        Financeiro.getInstancia().addPedidoPendente(this.pedido);
    }
}
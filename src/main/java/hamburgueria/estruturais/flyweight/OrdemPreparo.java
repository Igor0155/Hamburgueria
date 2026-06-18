package hamburgueria.estruturais.flyweight;

import hamburgueria.criacionais.builder.PedidoCliente;

public class OrdemPreparo {

    private PedidoCliente pedidoCliente;
    private Receita receita;

    public OrdemPreparo(PedidoCliente pedidoCliente, Receita receita) {
        this.pedidoCliente = pedidoCliente;
        this.receita = receita;
    }

    public PedidoCliente getPedidoCliente() {
        return pedidoCliente;
    }

    public Receita getReceita() {
        return receita;
    }

    public String emitirOrdem() {
        return "Ordem para Pedido #" + this.pedidoCliente.getNumeroPedido() +
                " (Cliente: " + this.pedidoCliente.getNomeCliente() + ")" +
                " -> Preparar: " + this.receita.getNomeLanche() +
                " | Tempo: " + this.receita.getTempoEstimadoMinutos() + " min";
    }
}
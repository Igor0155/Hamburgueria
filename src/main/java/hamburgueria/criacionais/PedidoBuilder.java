package hamburgueria.criacionais;

import hamburgueria.ComponenteCardapio;

public class PedidoBuilder {

    private Pedido pedido;

    public PedidoBuilder() {
        this.pedido = new Pedido();
    }

    public PedidoBuilder comCliente(String nome) {
        this.pedido.setNomeCliente(nome);
        return this;
    }

    public PedidoBuilder comPagamento(String tipoPagamento) {
        // Interligando com o Factory Method!
        this.pedido.setPagamento(PagamentoFactory.criarPagamento(tipoPagamento));
        return this;
    }

    public PedidoBuilder paraConsumo(String local) {
        // Interligando com o Abstract Factory!
        if (local.equalsIgnoreCase("DELIVERY")) {
            this.pedido.setEmbalagem(new FabricaDelivery());
        } else {
            this.pedido.setEmbalagem(new FabricaSalao());
        }
        return this;
    }

    public PedidoBuilder adicionarItem(ComponenteCardapio item) {
        // Interligando com o Composite/Decorator do cardápio!
        this.pedido.adicionarItem(item);
        return this;
    }

    public Pedido build() {
        if (this.pedido.getNomeCliente() == null || this.pedido.getNomeCliente().isEmpty()) {
            throw new IllegalArgumentException("O pedido precisa de um cliente.");
        }
        if (this.pedido.getQuantidadeItens() == 0) {
            throw new IllegalArgumentException("O pedido não pode estar vazio.");
        }
        return this.pedido;
    }
}
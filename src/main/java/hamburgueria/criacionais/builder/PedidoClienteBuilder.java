package hamburgueria.criacionais.builder;

import hamburgueria.criacionais.abstractfactory.FabricaAbstrata;
import hamburgueria.criacionais.factorymethod.IPagamento;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.composite.ItemCardapio;
import hamburgueria.estruturais.decorator.Lanche;

public class PedidoClienteBuilder {

    private PedidoCliente pedido;

    public PedidoClienteBuilder() {
        pedido = new PedidoCliente();
    }

    public PedidoCliente build() {
        if (!ConfiguracaoRestaurante.getInstance().isAceitandoPedidos()) {
            throw new IllegalStateException("Restaurante fechado!");
        }
        if (pedido.getNumeroPedido() == 0) {
            throw new IllegalArgumentException("Número inválido");
        }
        if (pedido.getNomeCliente().equals("")) {
            throw new IllegalArgumentException("Nome inválido");
        }
        return pedido;
    }

    public PedidoClienteBuilder setNumeroPedido(int numeroPedido) {
        pedido.setNumeroPedido(numeroPedido);
        return this;
    }

    public PedidoClienteBuilder setNomeCliente(String nomeCliente) {
        pedido.setNomeCliente(nomeCliente);
        return this;
    }

    public PedidoClienteBuilder setEnderecoEntrega(String enderecoEntrega) {
        pedido.setEnderecoEntrega(enderecoEntrega);
        return this;
    }

    public PedidoClienteBuilder setMetodoPagamento(IPagamento metodoPagamento) {
        pedido.setMetodoPagamento(metodoPagamento);
        return this;
    }

    public PedidoClienteBuilder setLanchePrincipal(Lanche lanche) {
        pedido.setLanchePrincipal(lanche);
        return this;
    }

    public PedidoClienteBuilder setItensExtras(ItemCardapio itensExtras) {
        pedido.setItensExtras(itensExtras);
        return this;
    }

    public PedidoClienteBuilder setFabricaEmbalagem(FabricaAbstrata fabrica) {
        pedido.setRecipienteLanche(fabrica.createRecipienteLanche());
        pedido.setRecipienteBebida(fabrica.createRecipienteBebida());
        pedido.setTransportePedido(fabrica.createTransportePedido());
        return this;
    }
}
package hamburgueria.criacionais.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.factorymethod.IPagamento;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.composite.ItemIndividual;
import hamburgueria.estruturais.decorator.AdicionalBacon;
import hamburgueria.estruturais.decorator.AdicionalCheddar;
import hamburgueria.estruturais.decorator.HamburguerBase;
import hamburgueria.estruturais.decorator.Lanche;

class PedidoClienteBuilderTest {

    private IPagamento pagamentoMock; // Usamos a implementação real do Factory Method

    @BeforeEach
    void setUp() {
        // Garante que o restaurante esteja aberto para a maioria dos testes
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pagamentoMock = new PagamentoPix();
    }

    @Test
    void deveRetornarExcecaoSeRestauranteEstiverFechado() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(false);

        try {
            PedidoClienteBuilder builder = new PedidoClienteBuilder();
            builder.setNumeroPedido(1).setNomeCliente("Igor").setMetodoPagamento(pagamentoMock).build();
            fail();
        } catch (IllegalStateException e) {
            assertEquals("O restaurante está fechado. Não é possível criar pedidos.", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaPedidoSemNumero() {
        try {
            PedidoClienteBuilder builder = new PedidoClienteBuilder();
            builder.setNomeCliente("Igor Gabriel").setMetodoPagamento(pagamentoMock).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Número do pedido inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaPedidoSemNomeCliente() {
        try {
            PedidoClienteBuilder builder = new PedidoClienteBuilder();
            builder.setNumeroPedido(101).setMetodoPagamento(pagamentoMock).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do cliente inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaPedidoSemPagamento() {
        try {
            PedidoClienteBuilder builder = new PedidoClienteBuilder();
            builder.setNumeroPedido(101).setNomeCliente("Igor").build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O pedido deve ter um método de pagamento", e.getMessage());
        }
    }

    @Test
    void deveConstruirPedidoComPagamentoViaFactoryMethod() {
        PedidoCliente pedido = new PedidoClienteBuilder()
                .setNumeroPedido(101)
                .setNomeCliente("Igor")
                .setMetodoPagamento(pagamentoMock)
                .build();

        assertEquals("Pagamento via PIX efetivado", pedido.getMetodoPagamento().processar());
    }

    @Test
    void deveConstruirPedidoComLancheViaDecorator() {
        // Integrando com Decorator
        HamburguerBase base = new HamburguerBase(20.0f);
        AdicionalBacon lancheComBacon = new AdicionalBacon(base);

        PedidoCliente pedido = new PedidoClienteBuilder()
                .setNumeroPedido(101)
                .setNomeCliente("Igor")
                .setMetodoPagamento(pagamentoMock)
                .setLanchePrincipal(lancheComBacon)
                .build();

        assertEquals("Hambúrguer Base + Bacon", pedido.getLanchePrincipal().getDescricao());
        assertEquals(25.0f, pedido.getLanchePrincipal().getPreco());
    }

    @Test
    void deveConstruirPedidoComItensExtrasViaComposite() {
        // Integrando com Composite
        ItemIndividual batata = new ItemIndividual("Batata Frita Grande", 18);

        PedidoCliente pedido = new PedidoClienteBuilder()
                .setNumeroPedido(101)
                .setNomeCliente("Igor")
                .setMetodoPagamento(pagamentoMock)
                .setItensExtras(batata)
                .build();

        assertEquals("Batata Frita Grande", pedido.getItensExtras().getDescricao());
    }

    @Test
    void deveConstruirPedidoComDadosBasicos() {
        PedidoCliente pedido = new PedidoClienteBuilder()
                .setNumeroPedido(101)
                .setNomeCliente("Igor")
                .setMetodoPagamento(pagamentoMock)
                .build();

        assertEquals(101, pedido.getNumeroPedido());
        assertEquals("Igor", pedido.getNomeCliente());
    }

    @Test
    void deveConstruirPedidoComLancheClonadoEEnergiaDeAbstractFactory() throws CloneNotSupportedException {

        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        Lanche lancheAmigo = new AdicionalBacon(new AdicionalCheddar(new HamburguerBase(20.0f)));
        Lanche meuLanche = lancheAmigo.clone();
        hamburgueria.criacionais.abstractfactory.FabricaAbstrata fabricaDelivery = new hamburgueria.criacionais.abstractfactory.FabricaDelivery();

        PedidoCliente pedido = new PedidoClienteBuilder()
                .setNumeroPedido(2002)
                .setNomeCliente("Igor Gabriel")
                .setLanchePrincipal(meuLanche)
                .setFabricaEmbalagem(fabricaDelivery)
                .build();

        assertEquals("Hambúrguer Base + Cheddar + Bacon", pedido.getLanchePrincipal().getDescricao());
        assertEquals("Lanche embalado na Caixa Térmica", pedido.getRecipienteLanche().embalar());
    }
}

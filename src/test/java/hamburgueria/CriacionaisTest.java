package hamburgueria.criacionais;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import hamburgueria.Produto; // Do nosso padrão Composite antigo

class CriacionaisTest {

    @Test
    void deveRetornarMesmaInstanciaSingleton() {
        ConfiguracaoRestaurante config1 = ConfiguracaoRestaurante.getInstancia();
        ConfiguracaoRestaurante config2 = ConfiguracaoRestaurante.getInstancia();

        config1.abrirRestaurante();

        // Se config1 abriu, config2 também deve ver aberto, pois são o mesmo objeto em
        // memória
        assertTrue(config2.isAberto());
        assertSame(config1, config2);
    }

    @Test
    void deveCriarPagamentoViaFactoryMethod() {
        Pagamento pag = PagamentoFactory.criarPagamento("PIX");
        assertTrue(pag instanceof PagamentoPix);
        assertEquals("Pagamento de R$ 50.0 processado via PIX.", pag.processarPagamento(50.0f));
    }

    @Test
    void deveCriarFamiliaEmbalagensAbstractFactory() {
        EmbalagemFactory fabrica = new FabricaDelivery();
        CaixaLanche caixa = fabrica.criarCaixa();
        CopoBebida copo = fabrica.criarCopo();

        assertEquals("Lanche na Caixa Térmica Lacrada", caixa.empacotar());
        assertEquals("Bebida no Copo de Papel Selado", copo.servir());
    }

    @Test
    void deveConstruirPedidoComBuilder() {
        // Aqui juntamos todos os padrões criacionais e estruturais!
        Pedido pedido = new PedidoBuilder()
                .comCliente("Igor Gabriel")
                .comPagamento("CARTAO")
                .paraConsumo("SALAO")
                .adicionarItem(new Produto("Batata", 15.0f)) // Item do Composite
                .build();

        assertEquals("Igor Gabriel", pedido.getNomeCliente());
        assertEquals(1, pedido.getQuantidadeItens());
        assertTrue(pedido.getPagamento() instanceof PagamentoCartao);
    }

    @Test
    void deveFalharBuilderSemCliente() {
        try {
            new PedidoBuilder().adicionarItem(new Produto("Batata", 15.0f)).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O pedido precisa de um cliente.", e.getMessage());
        }
    }
}
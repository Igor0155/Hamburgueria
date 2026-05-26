package hamburgueria.criacionais;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import hamburgueria.Produto;
import hamburgueria.criacionais.CaixaLanche;
import hamburgueria.criacionais.ConfiguracaoRestaurante;
import hamburgueria.criacionais.CopoBebida;
import hamburgueria.criacionais.EmbalagemFactory;
import hamburgueria.criacionais.FabricaDelivery;
import hamburgueria.criacionais.HamburguerPersonalizado;
import hamburgueria.criacionais.Pagamento;
import hamburgueria.criacionais.PagamentoFactory;
import hamburgueria.criacionais.Pedido;
import hamburgueria.criacionais.PedidoBuilder;

class CriacionaisTest {

    @Test
    void deveRetornarMesmaInstanciaSingleton() {
        ConfiguracaoRestaurante config1 = ConfiguracaoRestaurante.getInstancia();
        ConfiguracaoRestaurante config2 = ConfiguracaoRestaurante.getInstancia();

        config1.abrirRestaurante();

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

    @Test
    void deveClonarHamburguerEPermitirAlteracao() {
        HamburguerPersonalizado lancheOriginal = new HamburguerPersonalizado(
                "Brioche", "Picanha", "Prato", "Maionese Verde");

        HamburguerPersonalizado lancheClone = lancheOriginal.clone();

        // 1. Verifica se os valores foram copiados perfeitamente
        assertEquals("Brioche", lancheClone.getPao());
        assertEquals("Picanha", lancheClone.getCarne());

        // 2. Altera um ingrediente apenas no clone
        lancheClone.setPao("Integral");

        // 3. Verifica se a alteração ocorreu no clone e o original continuou intacto
        assertEquals("Integral", lancheClone.getPao());
        assertEquals("Brioche", lancheOriginal.getPao());

        // 4. Garante que são duas instâncias diferentes na memória
        assertNotSame(lancheOriginal, lancheClone);
    }
}
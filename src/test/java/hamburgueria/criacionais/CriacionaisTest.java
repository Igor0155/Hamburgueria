package hamburgueria.criacionais;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import hamburgueria.Produto;
import hamburgueria.criacionais.abstractfactory.FabricaDelivery;
import hamburgueria.criacionais.abstractfactory.Pedido;
import hamburgueria.criacionais.factorymethod.Pagamento;
import hamburgueria.criacionais.factorymethod.PagamentoCartaoCredito;
import hamburgueria.criacionais.factorymethod.PagamentoFactory;
import hamburgueria.criacionais.factorymethod.PagamentoPix;

class CriacionaisTest {

    @Test
    void deveCriarPagamentoViaFactoryMethod() {
        Pagamento pag = PagamentoFactory.criarPagamento("PIX");
        assertTrue(pag instanceof PagamentoPix);
        assertEquals("Pagamento de R$ 50.0 processado via PIX.", pag.processarPagamento(50.0f));
    }

    @Test
    void deveFalharAoCriarPagamentoInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PagamentoFactory.criarPagamento("BOLETO");
        });
        assertEquals("Tipo de pagamento não suportado.", exception.getMessage());
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
                .adicionarItem(new Produto("Batata", 15.0f))
                .build();

        assertEquals("Igor Gabriel", pedido.getNomeCliente());
        assertEquals(1, pedido.getQuantidadeItens());
        assertTrue(pedido.getPagamento() instanceof PagamentoCartaoCredito);
    }

    @Test
    void deveFalharBuilderSemCliente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PedidoBuilder().adicionarItem(new Produto("Batata", 15.0f)).build();
        });
        assertEquals("O pedido precisa de um cliente.", exception.getMessage());
    }

    @Test
    void deveClonarHamburguerEPermitirAlteracao() {
        HamburguerPersonalizado lancheOriginal = new HamburguerPersonalizado("Brioche", "Picanha", "Prato",
                "Maionese Verde");
        HamburguerPersonalizado lancheClone = lancheOriginal.clone();

        assertEquals("Brioche", lancheClone.getPao());
        assertEquals("Picanha", lancheClone.getCarne());

        lancheClone.setPao("Integral");

        assertEquals("Integral", lancheClone.getPao());
        assertEquals("Brioche", lancheOriginal.getPao());
        assertNotSame(lancheOriginal, lancheClone);
    }
}
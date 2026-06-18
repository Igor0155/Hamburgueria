package hamburgueria.comportamentais.templatemethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoDinheiro;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class ProcessadorFechamentoDeliveryTest {

    private PedidoCliente pedidoCaro;
    private PedidoCliente pedidoBarato;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        pedidoCaro = new PedidoClienteBuilder()
                .setNumeroPedido(20)
                .setNomeCliente("Cliente C")
                .setMetodoPagamento(new PagamentoDinheiro())
                .setLanchePrincipal(new HamburguerBase(45.0f)) // Acima de R$ 30,00
                .build();

        pedidoBarato = new PedidoClienteBuilder()
                .setNumeroPedido(21)
                .setNomeCliente("Cliente D")
                .setMetodoPagamento(new PagamentoDinheiro())
                .setLanchePrincipal(new HamburguerBase(15.0f)) // Abaixo de R$ 30,00
                .build();
    }

    @Test
    void deveRetornarLiberadoParaRotaNoDelivery() {
        ProcessadorFechamentoDelivery processador = new ProcessadorFechamentoDelivery();
        processador.setPedido(pedidoCaro);
        assertEquals("Liberado para Rota", processador.verificarStatusLiberacao());
    }

    @Test
    void deveRetornarRetidoValorMinimoNoDelivery() {
        ProcessadorFechamentoDelivery processador = new ProcessadorFechamentoDelivery();
        processador.setPedido(pedidoBarato);
        assertEquals("Retido: Valor Mínimo Não Atingido", processador.verificarStatusLiberacao());
    }

    @Test
    void deveRetornarTipoCanalDelivery() {
        ProcessadorFechamentoDelivery processador = new ProcessadorFechamentoDelivery();
        assertEquals("Delivery", processador.getTipoCanal());
    }

    @Test
    void deveEmitirComprovanteTemplateMethodDelivery() {
        ProcessadorFechamentoDelivery processador = new ProcessadorFechamentoDelivery();
        processador.setPedido(pedidoCaro);
        assertEquals("Delivery{pedido=20, cliente='Cliente C', status=Liberado para Rota}",
                processador.emitirComprovante());
    }

    @Test
    void deveCalcularTotalBaseDoLancheComSucesso() {
        ProcessadorFechamentoDelivery processador = new ProcessadorFechamentoDelivery();
        processador.setPedido(pedidoCaro);
        assertEquals(45.0f, processador.calcularTotalBase());
    }
}
package hamburgueria.comportamentais.templatemethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class ProcessadorFechamentoBalcaoTest {

    private PedidoCliente pedidoValido;
    private PedidoCliente pedidoZerado;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        pedidoValido = new PedidoClienteBuilder()
                .setNumeroPedido(10)
                .setNomeCliente("Cliente A")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(25.0f))
                .build();

        pedidoZerado = new PedidoClienteBuilder()
                .setNumeroPedido(11)
                .setNomeCliente("Cliente B")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(0.0f))
                .build();
    }

    @Test
    void deveRetornarLiberadoParaRetiradaNoBalcao() {
        ProcessadorFechamentoBalcao processador = new ProcessadorFechamentoBalcao();
        processador.setPedido(pedidoValido);
        assertEquals("Liberado para Retirada", processador.verificarStatusLiberacao());
    }

    @Test
    void deveRetornarRetidoPedidoVazioNoBalcao() {
        ProcessadorFechamentoBalcao processador = new ProcessadorFechamentoBalcao();
        processador.setPedido(pedidoZerado);
        assertEquals("Retido: Pedido Vazio", processador.verificarStatusLiberacao());
    }

    @Test
    void deveRetornarTipoCanalBalcao() {
        ProcessadorFechamentoBalcao processador = new ProcessadorFechamentoBalcao();
        assertEquals("Balcão", processador.getTipoCanal());
    }

    @Test
    void deveEmitirComprovanteTemplateMethodBalcao() {
        ProcessadorFechamentoBalcao processador = new ProcessadorFechamentoBalcao();
        processador.setPedido(pedidoValido);
        assertEquals("Balcão{pedido=10, cliente='Cliente A', status=Liberado para Retirada}",
                processador.emitirComprovante());
    }

    @Test
    void deveRetornarPedidoAssociadoEProtegerTotalContraNulo() {
        ProcessadorFechamentoBalcao processador = new ProcessadorFechamentoBalcao();
        processador.setPedido(null);

        assertNull(processador.getPedido());
        assertEquals(0.0f, processador.calcularTotalBase());
    }
}
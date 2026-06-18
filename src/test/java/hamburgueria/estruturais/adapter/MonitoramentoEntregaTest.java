package hamburgueria.estruturais.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.IPagamento;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;

class MonitoramentoEntregaTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {

        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        IPagamento pagamentoPix = new PagamentoPix();

        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(777)
                .setNomeCliente("Igor Gabriel")
                .setEnderecoEntrega("Rua Direita, 500")
                .setMetodoPagamento(pagamentoPix)
                .build();
    }

    @Test
    void deveGarantirQueOMonitoramentoProtegeOPedidoClienteOriginal() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        assertSame(pedidoReal, monitoramento.getPedido());
        assertEquals("Igor Gabriel", monitoramento.getPedido().getNomeCliente());
    }

    @Test
    void deveConverterStatusCriadoParaCodigoUm() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("CRIADO");
        assertEquals(1, monitoramento.obterCodigoExternoDeRastreio());
    }

    @Test
    void deveConverterStatusPreparandoParaCodigoDois() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("PREPARANDO");
        assertEquals(2, monitoramento.obterCodigoExternoDeRastreio());
    }

    @Test
    void deveConverterStatusSaiuParaEntregaParaCodigoTres() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("SAIU_ENTREGA");
        assertEquals(3, monitoramento.obterCodigoExternoDeRastreio());
    }

    @Test
    void deveConverterStatusConcluidoParaCodigoQuatro() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("CONCLUIDO");
        assertEquals(4, monitoramento.obterCodigoExternoDeRastreio());
    }

    @Test
    void deveConverterStatusInvalidoParaCodigoZero() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("MOTOBOY_CAIU");
        assertEquals(0, monitoramento.obterCodigoExternoDeRastreio());
    }

    @Test
    void deveRetornarStatusCriadoApósAtualizacao() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("CRIADO");
        assertEquals("CRIADO", monitoramento.obterStatusAtual());
    }

    @Test
    void deveRetornarStatusPreparandoApósAtualizacao() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("PREPARANDO");
        assertEquals("PREPARANDO", monitoramento.obterStatusAtual());
    }

    @Test
    void deveRetornarStatusSaiuEntregaApósAtualizacao() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("SAIU_ENTREGA");
        assertEquals("SAIU_ENTREGA", monitoramento.obterStatusAtual());
    }

    @Test
    void deveRetornarStatusConcluidoApósAtualizacao() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("CONCLUIDO");
        assertEquals("CONCLUIDO", monitoramento.obterStatusAtual());
    }

    @Test
    void deveRetornarStatusDesconhecidoQuandoNaoMapeado() {
        MonitoramentoEntrega monitoramento = new MonitoramentoEntrega(pedidoReal);
        monitoramento.atualizarStatusLogistica("MOTOBOY_CAIU");
        assertEquals("DESCONHECIDO", monitoramento.obterStatusAtual());
    }
}
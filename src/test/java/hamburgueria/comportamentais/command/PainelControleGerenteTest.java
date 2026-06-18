package hamburgueria.comportamentais.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;
import hamburgueria.estruturais.facade.Financeiro;
import hamburgueria.estruturais.facade.Logistica;

class PainelControleGerenteTest {

    private PainelControleGerente painelGerente;
    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        painelGerente = new PainelControleGerente();

        // INTEGRAÇÃO: Montagem do Pedido via Builder
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(700)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(30.0f))
                .build();

        // Limpeza dos Singletons da Facade para garantir isolamento em cada teste
        Logistica.getInstancia().removerPedidoPendente(pedidoReal);
        Financeiro.getInstancia().removerPedidoPendente(pedidoReal);
    }

    @Test
    void deveReterLogisticaViaComando() {
        ComandoPedido reter = new ComandoReterLogistica(pedidoReal);
        painelGerente.executarComando(reter);

        assertTrue(Logistica.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveLiberarLogisticaViaComando() {
        // Primeiro retemos manualmente para o teste fazer sentido
        Logistica.getInstancia().addPedidoPendente(pedidoReal);

        ComandoPedido liberar = new ComandoLiberarLogistica(pedidoReal);
        painelGerente.executarComando(liberar);

        assertFalse(Logistica.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveDesfazerRetencaoLogistica() {
        ComandoPedido reter = new ComandoReterLogistica(pedidoReal);
        painelGerente.executarComando(reter);
        painelGerente.desfazerUltimoComando();

        assertFalse(Logistica.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveDesfazerLiberacaoLogistica() {
        Logistica.getInstancia().addPedidoPendente(pedidoReal);

        ComandoPedido liberar = new ComandoLiberarLogistica(pedidoReal);
        painelGerente.executarComando(liberar);
        painelGerente.desfazerUltimoComando(); // Restaura a pendência

        assertTrue(Logistica.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveReterFinanceiroViaComando() {
        ComandoPedido reter = new ComandoReterFinanceiro(pedidoReal);
        painelGerente.executarComando(reter);

        assertTrue(Financeiro.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveAprovarFinanceiroViaComando() {
        Financeiro.getInstancia().addPedidoPendente(pedidoReal);

        ComandoPedido aprovar = new ComandoAprovarFinanceiro(pedidoReal);
        painelGerente.executarComando(aprovar);

        assertFalse(Financeiro.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveDesfazerRetencaoFinanceiro() {
        ComandoPedido reter = new ComandoReterFinanceiro(pedidoReal);
        painelGerente.executarComando(reter);
        painelGerente.desfazerUltimoComando();

        assertFalse(Financeiro.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveDesfazerAprovacaoFinanceiro() {
        Financeiro.getInstancia().addPedidoPendente(pedidoReal);

        ComandoPedido aprovar = new ComandoAprovarFinanceiro(pedidoReal);
        painelGerente.executarComando(aprovar);
        painelGerente.desfazerUltimoComando();

        assertTrue(Financeiro.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveAcumularComandosNoHistoricoDoPainel() {
        ComandoPedido cmd1 = new ComandoReterLogistica(pedidoReal);
        ComandoPedido cmd2 = new ComandoReterFinanceiro(pedidoReal);

        painelGerente.executarComando(cmd1);
        painelGerente.executarComando(cmd2);

        assertEquals(2, painelGerente.getQuantidadeComandosNoHistorico());
    }

    @Test
    void naoDeveLancarExcecaoAoDesfazerComHistoricoVazio() {
        // Se o painel for acionado sem histórico, a lista vazia não deve quebrar o
        // código
        assertDoesNotThrow(() -> painelGerente.desfazerUltimoComando());
        assertEquals(0, painelGerente.getQuantidadeComandosNoHistorico());
    }
}
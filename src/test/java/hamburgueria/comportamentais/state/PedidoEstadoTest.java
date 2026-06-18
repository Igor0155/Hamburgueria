package hamburgueria.comportamentais.state;

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
import hamburgueria.estruturais.facade.Logistica;

class PedidoEstadoTest {

    private PedidoCliente pedido;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        // Garante que a Facade está limpa para os testes passarem
        Logistica.getInstancia().removerPedidoPendente(pedido);

        pedido = new PedidoClienteBuilder()
                .setNumeroPedido(1)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(30.0f))
                .build();
    }

    @Test
    void deveIniciarPreparoPedidoCriado() {
        pedido.setEstado(PedidoEstadoCriado.getInstance());
        assertTrue(pedido.iniciarPreparo());
        assertEquals("Preparando", pedido.getNomeEstado());
    }

    @Test
    void naoDeveMarcarProntoPedidoCriado() {
        pedido.setEstado(PedidoEstadoCriado.getInstance());
        assertFalse(pedido.marcarPronto());
    }

    @Test
    void naoDeveEnviarParaEntregaPedidoCriado() {
        pedido.setEstado(PedidoEstadoCriado.getInstance());
        assertFalse(pedido.enviarParaEntrega());
    }

    @Test
    void naoDeveEntregarPedidoCriado() {
        pedido.setEstado(PedidoEstadoCriado.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    void deveCancelarPedidoCriado() {
        pedido.setEstado(PedidoEstadoCriado.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveIniciarPreparoPedidoPreparando() {
        pedido.setEstado(PedidoEstadoPreparando.getInstance());
        assertFalse(pedido.iniciarPreparo());
    }

    @Test
    void deveMarcarProntoPedidoPreparando() {
        pedido.setEstado(PedidoEstadoPreparando.getInstance());
        assertTrue(pedido.marcarPronto());
        assertEquals("Pronto", pedido.getNomeEstado());
    }

    @Test
    void naoDeveEnviarParaEntregaPedidoPreparando() {
        pedido.setEstado(PedidoEstadoPreparando.getInstance());
        assertFalse(pedido.enviarParaEntrega());
    }

    @Test
    void naoDeveEntregarPedidoPreparando() {
        pedido.setEstado(PedidoEstadoPreparando.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    void deveCancelarPedidoPreparando() {
        pedido.setEstado(PedidoEstadoPreparando.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getNomeEstado());
    }

    @Test
    void naoDeveIniciarPreparoPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertFalse(pedido.iniciarPreparo());
    }

    @Test
    void naoDeveMarcarProntoPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertFalse(pedido.marcarPronto());
    }

    @Test
    void deveEnviarParaEntregaPedidoProntoSemPendenciaFacade() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        // A Facade autoriza, então o State avança
        assertTrue(pedido.enviarParaEntrega());
        assertEquals("Em Rota", pedido.getNomeEstado());
    }

    @Test
    void naoDeveEnviarParaEntregaPedidoProntoComPendenciaFacade() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        // Sujamos a Facade!
        Logistica.getInstancia().addPedidoPendente(pedido);

        assertFalse(pedido.enviarParaEntrega());
        assertEquals("Pronto", pedido.getNomeEstado()); // Fica retido!
    }

    @Test
    void deveEntregarPedidoProntoNoBalcao() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertTrue(pedido.entregar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveCancelarPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertFalse(pedido.cancelar());
    }

    @Test
    void naoDeveIniciarPreparoPedidoEmRota() {
        pedido.setEstado(PedidoEstadoEmRota.getInstance());
        assertFalse(pedido.iniciarPreparo());
    }

    @Test
    void naoDeveMarcarProntoPedidoEmRota() {
        pedido.setEstado(PedidoEstadoEmRota.getInstance());
        assertFalse(pedido.marcarPronto());
    }

    @Test
    void naoDeveEnviarParaEntregaPedidoEmRota() {
        pedido.setEstado(PedidoEstadoEmRota.getInstance());
        assertFalse(pedido.enviarParaEntrega());
    }

    @Test
    void deveEntregarPedidoEmRota() {
        pedido.setEstado(PedidoEstadoEmRota.getInstance());
        assertTrue(pedido.entregar());
        assertEquals("Entregue", pedido.getNomeEstado());
    }

    @Test
    void naoDeveCancelarPedidoEmRota() {
        pedido.setEstado(PedidoEstadoEmRota.getInstance());
        assertFalse(pedido.cancelar());
    }

    // =======================================
    // ESTADO: ENTREGUE (Estado Final)
    // =======================================
    @Test
    void naoDeveIniciarPreparoPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.iniciarPreparo());
    }

    @Test
    void naoDeveMarcarProntoPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.marcarPronto());
    }

    @Test
    void naoDeveEnviarParaEntregaPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.enviarParaEntrega());
    }

    @Test
    void naoDeveEntregarPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    void naoDeveCancelarPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.cancelar());
    }

    @Test
    void naoDeveIniciarPreparoPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.iniciarPreparo());
    }

    @Test
    void naoDeveMarcarProntoPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.marcarPronto());
    }

    @Test
    void naoDeveEnviarParaEntregaPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.enviarParaEntrega());
    }

    @Test
    void naoDeveEntregarPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    void naoDeveCancelarPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.cancelar());
    }
}
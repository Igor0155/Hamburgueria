package hamburgueria.estruturais.facade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;

class PedidoFacadeTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        pedidoReal = new PedidoCliente();
        Cozinha.getInstancia().removerPedidoPendente(pedidoReal);
        Estoque.getInstancia().removerPedidoPendente(pedidoReal);
        Financeiro.getInstancia().removerPedidoPendente(pedidoReal);
        Logistica.getInstancia().removerPedidoPendente(pedidoReal);
    }

    @Test
    void deveRetornarPendenciaCozinhaNoDespacho() {
        Cozinha.getInstancia().addPedidoPendente(pedidoReal);
        assertFalse(pedidoReal.despacharPedido());
    }

    @Test
    void deveRetornarPendenciaEstoqueNoDespacho() {
        Estoque.getInstancia().addPedidoPendente(pedidoReal);
        assertFalse(pedidoReal.despacharPedido());
    }

    @Test
    void deveRetornarPendenciaFinanceiroNoDespacho() {
        Financeiro.getInstancia().addPedidoPendente(pedidoReal);
        assertFalse(pedidoReal.despacharPedido());
    }

    @Test
    void deveRetornarPendenciaLogisticaNoDespacho() {
        Logistica.getInstancia().addPedidoPendente(pedidoReal);
        assertFalse(pedidoReal.despacharPedido());
    }

    @Test
    void deveRetornarPedidoSemPendenciaProntoParaDespacho() {
        assertTrue(pedidoReal.despacharPedido());
    }

    @Test
    void deveConfirmarPedidoNaListaDePendencias() {
        Financeiro.getInstancia().addPedidoPendente(pedidoReal);
        assertTrue(Financeiro.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveRemoverPedidoDaListaDePendencias() {
        Financeiro.getInstancia().addPedidoPendente(pedidoReal);
        Financeiro.getInstancia().removerPedidoPendente(pedidoReal);
        assertFalse(Financeiro.getInstancia().verificarPedidoComPendencia(pedidoReal));
    }

    @Test
    void deveGarantirInstanciaUnicaParaCozinha() {
        assertSame(Cozinha.getInstancia(), Cozinha.getInstancia());
    }

    @Test
    void deveGarantirInstanciaUnicaParaEstoque() {
        assertSame(Estoque.getInstancia(), Estoque.getInstancia());
    }
}
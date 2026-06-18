package hamburgueria.estruturais.facade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PedidoTest {

    @Test
    void deveRetornarPendenciaCozinhaNoDespacho() {
        Pedido pedido = new Pedido();
        Cozinha.getInstancia().addPedidoPendente(pedido);

        assertFalse(pedido.despachar());
    }

    @Test
    void deveRetornarPendenciaEstoqueNoDespacho() {
        Pedido pedido = new Pedido();
        Estoque.getInstancia().addPedidoPendente(pedido);

        assertFalse(pedido.despachar());
    }

    @Test
    void deveRetornarPendenciaFinanceiroNoDespacho() {
        Pedido pedido = new Pedido();
        Financeiro.getInstancia().addPedidoPendente(pedido);

        assertFalse(pedido.despachar());
    }

    @Test
    void deveRetornarPendenciaLogisticaNoDespacho() {
        Pedido pedido = new Pedido();
        Logistica.getInstancia().addPedidoPendente(pedido);

        assertFalse(pedido.despachar());
    }

    @Test
    void deveRetornarPedidoSemPendenciaProntoParaDespacho() {
        Pedido pedido = new Pedido();

        assertTrue(pedido.despachar());
    }

    @Test
    void deveConfirmarPedidoNaListaDePendencias() {
        Pedido pedido = new Pedido();
        Financeiro.getInstancia().addPedidoPendente(pedido);

        assertTrue(Financeiro.getInstancia().verificarPedidoComPendencia(pedido));
    }

    @Test
    void deveRemoverPedidoDaListaDePendencias() {
        Pedido pedido = new Pedido();
        Financeiro.getInstancia().addPedidoPendente(pedido);
        Financeiro.getInstancia().removerPedidoPendente(pedido);

        assertFalse(Financeiro.getInstancia().verificarPedidoComPendencia(pedido));
    }

    @Test
    void deveGarantirInstanciaUnicaParaCozinha() {
        Cozinha instancia1 = Cozinha.getInstancia();
        Cozinha instancia2 = Cozinha.getInstancia();

        assertSame(instancia1, instancia2);
    }

    @Test
    void deveGarantirInstanciaUnicaParaEstoque() {
        Estoque instancia1 = Estoque.getInstancia();
        Estoque instancia2 = Estoque.getInstancia();

        assertSame(instancia1, instancia2);
    }

    @Test
    void deveGarantirInstanciaUnicaParaFinanceiro() {
        Financeiro instancia1 = Financeiro.getInstancia();
        Financeiro instancia2 = Financeiro.getInstancia();

        assertSame(instancia1, instancia2);
    }

    @Test
    void deveGarantirInstanciaUnicaParaLogistica() {
        Logistica instancia1 = Logistica.getInstancia();
        Logistica instancia2 = Logistica.getInstancia();

        assertSame(instancia1, instancia2);
    }
}
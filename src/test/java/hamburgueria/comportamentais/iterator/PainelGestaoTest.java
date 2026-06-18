package hamburgueria.comportamentais.iterator;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.comportamentais.state.PedidoEstadoCancelado;
import hamburgueria.comportamentais.state.PedidoEstadoEntregue;
import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class PainelGestaoTest {

    private FilaPedidos filaCheia;
    private FilaPedidos filaVazia;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        // Pedido 1: Lanche barato, Entregue
        PedidoCliente p1 = new PedidoClienteBuilder()
                .setNumeroPedido(1)
                .setNomeCliente("Igor")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(25.0f))
                .build();
        p1.setEstado(PedidoEstadoEntregue.getInstance());

        // Pedido 2: Lanche Premium (>= 40), Entregue
        PedidoCliente p2 = new PedidoClienteBuilder()
                .setNumeroPedido(2)
                .setNomeCliente("Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(45.0f))
                .build();
        p2.setEstado(PedidoEstadoEntregue.getInstance());

        // Pedido 3: Lanche Premium (>= 40), Cancelado
        PedidoCliente p3 = new PedidoClienteBuilder()
                .setNumeroPedido(3)
                .setNomeCliente("Jeanne")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(50.0f))
                .build();
        p3.setEstado(PedidoEstadoCancelado.getInstance());

        // Pedido 4: Lanche barato, Criado (nem entregue, nem cancelado)
        PedidoCliente p4 = new PedidoClienteBuilder()
                .setNumeroPedido(4)
                .setNomeCliente("Visitante")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(15.0f))
                .build();

        filaCheia = new FilaPedidos(p1, p2, p3, p4);
        filaVazia = new FilaPedidos(); // Fila inicializada sem argumentos
    }

    @Test
    void deveContarTotalPedidosNaFila() {
        assertEquals(4, PainelGestao.contarTotalPedidos(filaCheia));
    }

    @Test
    void deveContarApenasPedidosEntregues() {
        // Pedidos p1 e p2 estão com o Status 'Entregue'
        assertEquals(2, PainelGestao.contarPedidosEntregues(filaCheia));
    }

    @Test
    void deveContarApenasPedidosCancelados() {
        // Somente o pedido p3 foi 'Cancelado'
        assertEquals(1, PainelGestao.contarPedidosCancelados(filaCheia));
    }

    @Test
    void deveContarApenasPedidosPremiumAcimaDeQuarentaReais() {
        // Pedidos p2 (45.0) e p3 (50.0)
        assertEquals(2, PainelGestao.contarPedidosPremium(filaCheia));
    }

    @Test
    void deveRetornarZeroAoContarTotalPedidosEmFilaVazia() {
        assertEquals(0, PainelGestao.contarTotalPedidos(filaVazia));
    }

    @Test
    void deveRetornarZeroAoContarEntreguesEmFilaVazia() {
        assertEquals(0, PainelGestao.contarPedidosEntregues(filaVazia));
    }

    @Test
    void deveRetornarZeroAoContarCanceladosEmFilaVazia() {
        assertEquals(0, PainelGestao.contarPedidosCancelados(filaVazia));
    }

    @Test
    void deveRetornarZeroAoContarPremiumEmFilaVazia() {
        assertEquals(0, PainelGestao.contarPedidosPremium(filaVazia));
    }

    @Test
    void deveRetornarInstanciaDoIteratorNativoCorretamente() {
        Iterator<PedidoCliente> iterador = filaCheia.iterator();
        assertNotNull(iterador);
    }

    @Test
    void deveConfirmarQueIteratorPossuiProximoElementoNaFilaCheia() {
        Iterator<PedidoCliente> iterador = filaCheia.iterator();
        assertTrue(iterador.hasNext());
    }
}
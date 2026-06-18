package hamburgueria.comportamentais.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class NotificacaoClienteTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(55)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(30.0f))
                .build();
    }

    @Test
    void deveRetornarNomeDoClienteNotificado() {
        NotificacaoCliente cliente = new NotificacaoCliente("Igor");
        assertEquals("Igor", cliente.getNome());
    }

    @Test
    void deveAlterarNomeDoClienteNotificado() {
        NotificacaoCliente cliente = new NotificacaoCliente("Igor");
        cliente.setNome("Igor Rodrigues");
        assertEquals("Igor Rodrigues", cliente.getNome());
    }

    @Test
    void deveRetornarAtributosDoAcompanhamento() {
        PedidoAcompanhamento acompanhamento = new PedidoAcompanhamento(1, 10, "Cozinha", pedidoReal);

        assertEquals(1, acompanhamento.getIdEtapa());
        assertEquals(10, acompanhamento.getSequencial());
        assertEquals("Cozinha", acompanhamento.getNomeEtapa());
        assertSame(pedidoReal, acompanhamento.getPedido());
    }

    @Test
    void devePermitirAlterarAtributosDoAcompanhamento() {
        PedidoAcompanhamento acompanhamento = new PedidoAcompanhamento(1, 10, "Cozinha", pedidoReal);
        PedidoCliente novoPedido = new PedidoCliente();

        acompanhamento.setIdEtapa(2);
        acompanhamento.setSequencial(20);
        acompanhamento.setNomeEtapa("Entrega");
        acompanhamento.setPedido(novoPedido);

        assertEquals(2, acompanhamento.getIdEtapa());
        assertEquals(20, acompanhamento.getSequencial());
        assertEquals("Entrega", acompanhamento.getNomeEtapa());
        assertSame(novoPedido, acompanhamento.getPedido());
    }

    @Test
    void deveNotificarUmCliente() {
        PedidoAcompanhamento acompanhamento = new PedidoAcompanhamento(2026, 1, "Grelha", pedidoReal);
        NotificacaoCliente cliente = new NotificacaoCliente("Cliente 1");
        cliente.registrarNoAcompanhamento(acompanhamento);
        acompanhamento.atualizarStatusFase();

        String esperado = "Cliente 1, pedido updated no Acompanhamento{idEtapa=2026, sequencial=1, nomeEtapa='Grelha', pedidoId=55}";
        assertEquals(esperado, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarVariosClientes() {
        PedidoAcompanhamento acompanhamento = new PedidoAcompanhamento(2026, 1, "Grelha", pedidoReal);
        NotificacaoCliente cliente1 = new NotificacaoCliente("Cliente 1");
        NotificacaoCliente cliente2 = new NotificacaoCliente("Cliente 2");

        cliente1.registrarNoAcompanhamento(acompanhamento);
        cliente2.registrarNoAcompanhamento(acompanhamento);
        acompanhamento.atualizarStatusFase();

        String esperado1 = "Cliente 1, pedido updated no Acompanhamento{idEtapa=2026, sequencial=1, nomeEtapa='Grelha', pedidoId=55}";
        String esperado2 = "Cliente 2, pedido updated no Acompanhamento{idEtapa=2026, sequencial=1, nomeEtapa='Grelha', pedidoId=55}";

        assertEquals(esperado1, cliente1.getUltimaNotificacao());
        assertEquals(esperado2, cliente2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteSemRegistro() {
        PedidoAcompanhamento acompanhamento = new PedidoAcompanhamento(2026, 1, "Grelha", pedidoReal);
        NotificacaoCliente cliente = new NotificacaoCliente("Cliente 1");
        acompanhamento.atualizarStatusFase();

        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteApenasNoAcompanhamentoEspecifico() {
        PedidoAcompanhamento acompanhamentoA = new PedidoAcompanhamento(2026, 1, "Grelha", pedidoReal);
        PedidoAcompanhamento acompanhamentoB = new PedidoAcompanhamento(2026, 2, "Montagem", pedidoReal);

        NotificacaoCliente cliente1 = new NotificacaoCliente("Cliente 1");
        NotificacaoCliente cliente2 = new NotificacaoCliente("Cliente 2");

        cliente1.registrarNoAcompanhamento(acompanhamentoA);
        cliente2.registrarNoAcompanhamento(acompanhamentoB);
        acompanhamentoA.atualizarStatusFase();

        String esperado1 = "Cliente 1, pedido updated no Acompanhamento{idEtapa=2026, sequencial=1, nomeEtapa='Grelha', pedidoId=55}";

        assertEquals(esperado1, cliente1.getUltimaNotificacao());
        assertNull(cliente2.getUltimaNotificacao());
    }
}
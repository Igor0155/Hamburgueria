package hamburgueria.comportamentais.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.comportamentais.state.PedidoEstadoCancelado;
import hamburgueria.comportamentais.state.PedidoEstadoPreparando;
import hamburgueria.comportamentais.state.PedidoEstadoPronto;
import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class ZeladorHistoricoPedidoTest {

    private PedidoCliente pedidoReal;
    private ZeladorHistoricoPedido zelador;

    @BeforeEach
    void setUp() {
        // INTEGRAÇÃO: Constrói o ecossistema e o pedido base
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(1)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(25.0f))
                .build(); // O pedido já nasce no estado "Criado"

        zelador = new ZeladorHistoricoPedido(pedidoReal);
    }

    // ==========================================

    @Test
    void deveLancarExcecaoAoCriarZeladorComPedidoNulo() {
        try {
            ZeladorHistoricoPedido zeladorInvalido = new ZeladorHistoricoPedido(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O zelador precisa de um pedido válido para monitorar", e.getMessage());
        }
    }

    @Test
    void deveArmazenarUmEstadoNoHistorico() {
        zelador.salvarEstadoAtual();
        assertEquals(1, zelador.getTotalEstadosSalvos());
    }

    @Test
    void deveArmazenarMultiplosEstadosNoHistorico() {
        zelador.salvarEstadoAtual(); // Salva o estado Criado (índice 0)

        pedidoReal.setEstado(PedidoEstadoPreparando.getInstance());
        zelador.salvarEstadoAtual(); // Salva o estado Preparando (índice 1)

        pedidoReal.setEstado(PedidoEstadoPronto.getInstance());
        zelador.salvarEstadoAtual(); // Salva o estado Pronto (índice 2)

        assertEquals(3, zelador.getTotalEstadosSalvos());
    }

    @Test
    void deveRetornarOMementoCorretoPeloIndice() {
        zelador.salvarEstadoAtual(); // Índice 0 (Criado)

        pedidoReal.setEstado(PedidoEstadoPreparando.getInstance());
        zelador.salvarEstadoAtual(); // Índice 1 (Preparando)

        PedidoMemento memento = zelador.getMemento(1);
        assertEquals("Preparando", memento.getEstadoSalvo().getNomeEstado());
    }

    @Test
    void deveRestaurarPedidoParaOEstadoInicialCriado() {
        zelador.salvarEstadoAtual(); // Salva "Criado" no índice 0

        pedidoReal.setEstado(PedidoEstadoCancelado.getInstance()); // Cliente cancelou
        zelador.salvarEstadoAtual(); // Salva "Cancelado" no índice 1

        // O gerente decide desfazer o cancelamento e voltar para o estado inicial
        zelador.restaurarEstado(0);

        assertEquals("Criado", pedidoReal.getNomeEstado());
    }

    @Test
    void deveRestaurarPedidoParaUmEstadoIntermediario() {
        zelador.salvarEstadoAtual(); // 0: Criado

        pedidoReal.setEstado(PedidoEstadoPreparando.getInstance());
        zelador.salvarEstadoAtual(); // 1: Preparando

        pedidoReal.setEstado(PedidoEstadoPronto.getInstance());
        zelador.salvarEstadoAtual(); // 2: Pronto

        // Retorna a etapa porque faltou um ingrediente na bandeja
        zelador.restaurarEstado(1);

        assertEquals("Preparando", pedidoReal.getNomeEstado());
    }

    @Test
    void deveLancarExcecaoAoRestaurarComIndiceNegativo() {
        zelador.salvarEstadoAtual();

        try {
            zelador.restaurarEstado(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice de histórico inválido", e.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoAoRestaurarComIndiceMaiorQueOArmazenado() {
        zelador.salvarEstadoAtual(); // Só existe o índice 0

        try {
            zelador.restaurarEstado(5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice de histórico inválido", e.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoAoBuscarMementoComIndiceInvalido() {
        zelador.salvarEstadoAtual();

        try {
            zelador.getMemento(99);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice de histórico inválido", e.getMessage());
        }
    }
}
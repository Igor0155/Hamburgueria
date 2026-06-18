package hamburgueria.estruturais.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class ModalidadeAtendimentoTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        // 1. Integração: Prepara o ambiente
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        // 2. Integração: Cria um lanche fixo de R$ 100,00 (Decorator) para facilitar os
        // testes matemáticos
        HamburguerBase lancheCemReais = new HamburguerBase(100.0f);

        // 3. Integração: Constrói o objeto no Builder
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(1)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(lancheCemReais)
                .build();
    }

    @Test
    void deveRetornarExcecaoParaPedidoNuloNaModalidade() {
        try {
            ModalidadeAtendimento balcao = new AtendimentoBalcao(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Pedido ou lanche principal não pode ser nulo", e.getMessage());
        }
    }

    @Test
    void deveCalcularAtendimentoBalcaoComFidelidadeVisitante() {
        ModalidadeAtendimento atendimento = new AtendimentoBalcao(pedidoReal);
        atendimento.setFidelidade(new FidelidadeVisitante());
        assertEquals(100.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoBalcaoComFidelidadePrata() {
        ModalidadeAtendimento atendimento = new AtendimentoBalcao(pedidoReal);
        atendimento.setFidelidade(new FidelidadePrata());
        assertEquals(90.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoBalcaoComFidelidadeOuro() {
        ModalidadeAtendimento atendimento = new AtendimentoBalcao(pedidoReal);
        atendimento.setFidelidade(new FidelidadeOuro());
        assertEquals(80.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoBalcaoComFidelidadeBlack() {
        ModalidadeAtendimento atendimento = new AtendimentoBalcao(pedidoReal);
        atendimento.setFidelidade(new FidelidadeBlack());
        assertEquals(70.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoDeliveryComFidelidadeVisitante() {
        ModalidadeAtendimento atendimento = new AtendimentoDelivery(pedidoReal);
        atendimento.setFidelidade(new FidelidadeVisitante());
        assertEquals(115.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoDeliveryComFidelidadePrata() {
        ModalidadeAtendimento atendimento = new AtendimentoDelivery(pedidoReal);
        atendimento.setFidelidade(new FidelidadePrata());
        assertEquals(103.5f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoDeliveryComFidelidadeOuro() {
        ModalidadeAtendimento atendimento = new AtendimentoDelivery(pedidoReal);
        atendimento.setFidelidade(new FidelidadeOuro());
        assertEquals(92.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoDeliveryComFidelidadeBlack() {
        ModalidadeAtendimento atendimento = new AtendimentoDelivery(pedidoReal);
        atendimento.setFidelidade(new FidelidadeBlack());
        assertEquals(80.5f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoCorporativoComFidelidadeVisitante() {
        ModalidadeAtendimento atendimento = new AtendimentoCorporativo(pedidoReal);
        atendimento.setFidelidade(new FidelidadeVisitante());
        assertEquals(80.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoCorporativoComFidelidadePrata() {
        ModalidadeAtendimento atendimento = new AtendimentoCorporativo(pedidoReal);
        atendimento.setFidelidade(new FidelidadePrata());
        assertEquals(72.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoCorporativoComFidelidadeOuro() {
        ModalidadeAtendimento atendimento = new AtendimentoCorporativo(pedidoReal);
        atendimento.setFidelidade(new FidelidadeOuro());
        assertEquals(64.0f, atendimento.calcularValorFinal(), 0.01f);
    }

    @Test
    void deveCalcularAtendimentoCorporativoComFidelidadeBlack() {
        ModalidadeAtendimento atendimento = new AtendimentoCorporativo(pedidoReal);
        atendimento.setFidelidade(new FidelidadeBlack());
        assertEquals(56.0f, atendimento.calcularValorFinal(), 0.01f);
    }
}
package hamburgueria.comportamentais.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class FechamentoPedidoTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        // 1. Integração: Abre o restaurante
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        // 2. Integração: Cria um lanche fixo de R$ 100,00 (Decorator)
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
    void deveRetornarExcecaoParaCalculoComPedidoNulo() {
        try {
            FechamentoPedido fechamento = new FechamentoPedido();
            fechamento.calcularPrecoNormal(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Pedido inválido para cálculo", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaCalculoComPedidoSemLanche() {
        // Cria um pedido burlado sem usar o Builder oficial para forçar o erro
        PedidoCliente pedidoVazio = new PedidoCliente();

        try {
            FechamentoPedido fechamento = new FechamentoPedido();
            fechamento.calcularPrecoNormal(pedidoVazio);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Pedido inválido para cálculo", e.getMessage());
        }
    }

    @Test
    void deveRetornarZeroNoValorProcessadoInicialmente() {
        FechamentoPedido fechamento = new FechamentoPedido();
        assertEquals(0.0f, fechamento.getValorProcessado(), 0.01f);
    }

    @Test
    void deveCalcularPrecoNormalSemAlteracoes() {
        FechamentoPedido fechamento = new FechamentoPedido();
        fechamento.calcularPrecoNormal(pedidoReal);
        // Base R$ 100,00 -> Esperado: 100.0f
        assertEquals(100.0f, fechamento.getValorProcessado(), 0.01f);
    }

    @Test
    void deveCalcularDescontoAniversario() {
        FechamentoPedido fechamento = new FechamentoPedido();
        fechamento.calcularDescontoAniversario(pedidoReal);
        // Base R$ 100,00 com 20% off -> Esperado: 80.0f
        assertEquals(80.0f, fechamento.getValorProcessado(), 0.01f);
    }

    @Test
    void deveCalcularDescontoPrimeiraCompra() {
        FechamentoPedido fechamento = new FechamentoPedido();
        fechamento.calcularDescontoPrimeiraCompra(pedidoReal);
        // Base R$ 100,00 com 15% off -> Esperado: 85.0f
        assertEquals(85.0f, fechamento.getValorProcessado(), 0.01f);
    }

    @Test
    void deveCalcularTaxaMadrugada() {
        FechamentoPedido fechamento = new FechamentoPedido();
        fechamento.calcularTaxaMadrugada(pedidoReal);
        // Base R$ 100,00 com 20% extra -> Esperado: 120.0f
        assertEquals(120.0f, fechamento.getValorProcessado(), 0.01f);
    }

    @Test
    void deveCalcularAcrescimoFeriado() {
        FechamentoPedido fechamento = new FechamentoPedido();
        fechamento.calcularAcrescimoFeriado(pedidoReal);
        // Base R$ 100,00 com 10% extra -> Esperado: 110.0f
        assertEquals(110.0f, fechamento.getValorProcessado(), 0.01f);
    }
}
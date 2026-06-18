package hamburgueria.comportamentais.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class FechamentoCaixaTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(1001)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(40.0f)) // O lanche custa R$ 40,00
                .build();
    }

    @Test
    void deveRetornarExcecaoParaFechamentoComPedidoNulo() {
        try {
            FechamentoCaixa caixa = new FechamentoCaixa(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Pedido inválido para fechamento de caixa", e.getMessage());
        }
    }

    @Test
    void deveRetornarOPedidoAssociadoAoCaixa() {
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoReal);
        assertSame(pedidoReal, caixa.getPedido());
    }

    @Test
    void deveCobrarPedidoComCupomDeDescontoPelaMetade() {
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoReal);

        // Regra Dinâmica: Lanche pela metade do preço + 10 reais de frete
        // Matemática: (40.0 / 2) + 10.0 = 30.0
        String regraCupom = "lanchePreco / 2 + taxaFrete";
        double frete = 10.0;

        assertEquals(30.0, caixa.cobrarComCupomDinamico(regraCupom, frete));
    }

    @Test
    void deveCobrarPedidoComRegraDeTaxaNoturnaDobrada() {
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoReal);

        // Regra Dinâmica: Lanche normal + Frete cobrado em dobro na madrugada
        // Matemática: 40.0 + (15.0 * 2) = 70.0
        String regraTaxaNoturna = "lanchePreco + taxaFrete * 2";
        double frete = 15.0;

        assertEquals(70.0, caixa.cobrarComCupomDinamico(regraTaxaNoturna, frete));
    }

    @Test
    void deveCobrarPedidoComRegraDeIsencaoDeFrete() {
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoReal);

        // Regra Dinâmica: Paga só o lanche, isenta o frete (multiplica frete por 0)
        // Matemática: 40.0 + (15.0 * 0) = 40.0
        String regraFreteGratis = "lanchePreco + taxaFrete * 0";
        double frete = 15.0;

        assertEquals(40.0, caixa.cobrarComCupomDinamico(regraFreteGratis, frete));
    }
}
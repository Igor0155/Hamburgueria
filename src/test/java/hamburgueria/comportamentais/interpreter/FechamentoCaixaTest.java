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

    private PedidoCliente pedidoSemCupom;
    private PedidoCliente pedidoComCupom;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        // Pedido 1: Cliente normal (Lanche de R$ 40,00)
        pedidoSemCupom = new PedidoClienteBuilder()
                .setNumeroPedido(1)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(40.0f))
                .build();

        // Pedido 2: Cliente com Cupom da Madrugada (Lanche de R$ 40,00)
        CupomDesconto cupomMadrugada = new CupomDesconto("MADRUGA20", "lanchePreco + taxaFrete * 2");

        pedidoComCupom = new PedidoClienteBuilder()
                .setNumeroPedido(2)
                .setNomeCliente("Jeanne")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(40.0f))
                .setCupomDesconto(cupomMadrugada) // A MÁGICA DA INTEGRAÇÃO AQUI
                .build();
    }

    @Test
    void deveRetornarCodigoDoCupomDesconto() {
        CupomDesconto cupom = new CupomDesconto("BLACK50", "lanchePreco / 2");
        assertEquals("BLACK50", cupom.getCodigo());
    }

    @Test
    void deveRetornarRegraMatematicaDoCupomDesconto() {
        CupomDesconto cupom = new CupomDesconto("BLACK50", "lanchePreco / 2");
        assertEquals("lanchePreco / 2", cupom.getRegraMatematica());
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
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoSemCupom);
        assertSame(pedidoSemCupom, caixa.getPedido());
    }

    @Test
    void deveCobrarPedidoSemCupomUsandoRegraPadrao() {
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoSemCupom);
        double frete = 15.0;

        // Regra padrão escondida no caixa: lanchePreco + taxaFrete -> 40.0 + 15.0 =
        // 55.0
        assertEquals(55.0, caixa.processarPagamentoFinal(frete));
    }

    @Test
    void deveCobrarPedidoLendoARegraDoCupomAcopladoNoObjeto() {
        FechamentoCaixa caixa = new FechamentoCaixa(pedidoComCupom);
        double frete = 15.0;

        // O caixa deve ignorar a regra padrão e ler o cupom "MADRUGA20" que está no
        // Builder
        // Regra do cupom: lanchePreco + taxaFrete * 2 -> 40.0 + (15.0 * 2) = 70.0
        assertEquals(70.0, caixa.processarPagamentoFinal(frete));
    }
}
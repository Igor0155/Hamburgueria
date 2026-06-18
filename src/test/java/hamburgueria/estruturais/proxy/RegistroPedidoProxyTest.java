package hamburgueria.estruturais.proxy;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.IPagamento;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.AdicionalBacon;
import hamburgueria.estruturais.decorator.HamburguerBase;
import hamburgueria.estruturais.decorator.Lanche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroPedidoProxyTest {

    @BeforeEach
    void setUp() {
        BancoDeDadosPedidos.limparCache();
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);

        // INTEGRAÇÃO: Instanciando as interfaces e objetos dos outros padrões
        IPagamento pagamentoPix = new PagamentoPix();
        Lanche lancheComBacon = new AdicionalBacon(new HamburguerBase(20.0f)); // Custa 25.0f

        // INTEGRAÇÃO: Construindo o objeto unificado no Builder
        PedidoCliente pedido = new PedidoClienteBuilder()
                .setNumeroPedido(1001)
                .setNomeCliente("Igor Gabriel")
                .setEnderecoEntrega("Avenida Rio Branco, 2000")
                .setMetodoPagamento(pagamentoPix)
                .setLanchePrincipal(lancheComBacon)
                .build();

        // Salva o objeto monstruoso no banco
        BancoDeDadosPedidos.addPedido(pedido);
    }

    // ==========================================
    // TESTES DE ACESSO PÚBLICO (Proxy)
    // ==========================================

    @Test
    void deveRetornarDadosDeEntregaParaQualquerPessoa() {
        IRegistroPedido proxy = new RegistroPedidoProxy(1001);
        PedidoCliente pedidoCarregado = proxy.obterDadosEntrega();

        assertEquals("Igor Gabriel", pedidoCarregado.getNomeCliente());
        assertEquals("Avenida Rio Branco, 2000", pedidoCarregado.getEnderecoEntrega());
    }

    @Test
    void deveRetornarMetodoDePagamentoParaGerente() {
        Funcionario gerente = new Funcionario("Carlos", true);
        IRegistroPedido proxy = new RegistroPedidoProxy(1001);

        IPagamento pagamentoSeguro = proxy.obterMetodoPagamento(gerente);

        assertEquals("Pagamento via PIX efetivado", pagamentoSeguro.processar());
    }

    @Test
    void deveLancarExcecaoAoConsultarPagamentoSemSerGerente() {
        Funcionario atendente = new Funcionario("Maria", false);
        IRegistroPedido proxy = new RegistroPedidoProxy(1001);

        try {
            proxy.obterMetodoPagamento(atendente);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Acesso Negado: Apenas gerentes podem ver os dados de pagamento.", e.getMessage());
        }
    }

    @Test
    void deveRetornarFaturamentoLiquidoParaGerente() {
        Funcionario gerente = new Funcionario("Carlos", true);
        IRegistroPedido proxy = new RegistroPedidoProxy(1001);

        // Lanche custa 25.0. O custo é 40% (10.0). Lucro líquido deve ser 15.0.
        Float lucro = proxy.obterFaturamentoLiquido(gerente);

        assertEquals(15.0f, lucro, 0.01f);
    }

    @Test
    void deveLancarExcecaoAoConsultarFaturamentoLiquidoSemSerGerente() {
        Funcionario motoboy = new Funcionario("João", false);
        IRegistroPedido proxy = new RegistroPedidoProxy(1001);

        try {
            proxy.obterFaturamentoLiquido(motoboy);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Acesso Negado: Apenas gerentes podem ver o faturamento líquido.", e.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoExistirNoBancoDeDados() {
        IRegistroPedido proxy = new RegistroPedidoProxy(9999);

        try {
            // O proxy só vai estourar o erro do banco na hora que formos ler o dado
            proxy.obterDadosEntrega();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Pedido não encontrado no banco de dados", e.getMessage());
        }
    }

    @Test
    void deveRetornarNullSeConsultarDiretoNoBancoSemCriarProxy() {
        assertNull(BancoDeDadosPedidos.getPedido(9999));
    }

    @Test
    void deveRetornarNomeDoFuncionario() {
        Funcionario f = new Funcionario("Pedro", true);
        assertEquals("Pedro", f.getNome());
    }

    @Test
    void deveAlterarNomeDoFuncionario() {
        Funcionario f = new Funcionario("Pedro", true);
        f.setNome("Pedro Silva");
        assertEquals("Pedro Silva", f.getNome());
    }

    @Test
    void deveRetornarStatusGerenteVerdadeiro() {
        Funcionario f = new Funcionario("Ana", true);
        assertTrue(f.isGerente());
    }

    @Test
    void deveRetornarStatusGerenteFalsoEAlterar() {
        Funcionario f = new Funcionario("Beto", false);
        assertFalse(f.isGerente());

        f.setGerente(true);
        assertTrue(f.isGerente());
    }
}
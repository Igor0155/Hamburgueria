package hamburgueria.estruturais.flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.IPagamento;
import hamburgueria.criacionais.factorymethod.PagamentoDinheiro;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;

class OrdemPreparoFlyweightTest {

    private PedidoCliente pedidoBase;

    @BeforeEach
    void setUp() {
        // 1. Limpa a memória RAM do servidor (Factory) antes de cada teste
        ReceitaFactory.limparCache();

        // 2. Prepara o ecossistema integrado para gerar um pedido real
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        IPagamento pagamentoDinheiro = new PagamentoDinheiro();

        pedidoBase = new PedidoClienteBuilder()
                .setNumeroPedido(500)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(pagamentoDinheiro)
                .build();
    }

    @Test
    void deveRetornarNomeDaReceita() {
        Receita receita = new Receita("Smash Burger", "Chapar carne 2 min cada lado", 5);
        assertEquals("Smash Burger", receita.getNomeLanche());
    }

    @Test
    void deveRetornarModoDePreparoDaReceita() {
        Receita receita = new Receita("Smash Burger", "Chapar carne 2 min cada lado", 5);
        assertEquals("Chapar carne 2 min cada lado", receita.getModoDePreparo());
    }

    @Test
    void deveRetornarTempoEstimadoDaReceita() {
        Receita receita = new Receita("Smash Burger", "Chapar carne 2 min cada lado", 5);
        assertEquals(5, receita.getTempoEstimadoMinutos());
    }

    @Test
    void deveRetornarZeroReceitasNoCacheInicialmente() {
        assertEquals(0, ReceitaFactory.getTotalReceitasEmMemoria());
    }

    @Test
    void deveReaproveitarMesmaInstanciaParaLanchesIguais() {
        Receita r1 = ReceitaFactory.getReceita("X-Bacon", "Fritar bacon e carne", 10);
        Receita r2 = ReceitaFactory.getReceita("X-Bacon", "Fritar bacon e carne", 10);

        assertSame(r1, r2, "Deve apontar para o mesmo espaço de memória");
    }

    @Test
    void deveCriarInstanciasDiferentesParaLanchesDiferentes() {
        Receita r1 = ReceitaFactory.getReceita("X-Bacon", "Fritar bacon", 10);
        Receita r2 = ReceitaFactory.getReceita("X-Salada", "Lavar alface", 8);

        assertNotSame(r1, r2, "Devem ser objetos diferentes na memória");
    }

    @Test
    void deveContabilizarTamanhoCorretoDoCache() {
        ReceitaFactory.getReceita("X-Bacon", "Fritar bacon", 10);
        ReceitaFactory.getReceita("X-Bacon", "Fritar bacon", 10); // Reaproveitado
        ReceitaFactory.getReceita("X-Salada", "Lavar alface", 8); // Novo

        assertEquals(2, ReceitaFactory.getTotalReceitasEmMemoria());
    }

    @Test
    void deveRetornarPedidoClienteDaOrdemDePreparo() {
        Receita receita = ReceitaFactory.getReceita("X-Burger", "Pão e Carne", 5);
        OrdemPreparo ordem = new OrdemPreparo(pedidoBase, receita);

        assertSame(pedidoBase, ordem.getPedidoCliente());
    }

    @Test
    void deveRetornarReceitaDaOrdemDePreparo() {
        Receita receita = ReceitaFactory.getReceita("X-Burger", "Pão e Carne", 5);
        OrdemPreparo ordem = new OrdemPreparo(pedidoBase, receita);

        assertSame(receita, ordem.getReceita());
    }

    @Test
    void deveEmitirOrdemDePreparoIntegradaFormatada() {
        Receita receita = ReceitaFactory.getReceita("Mega Smash", "Pão brioche e 2x carnes", 12);
        OrdemPreparo ordem = new OrdemPreparo(pedidoBase, receita);

        String esperado = "Ordem para Pedido #500 (Cliente: Igor Gabriel) -> Preparar: Mega Smash | Tempo: 12 min";
        assertEquals(esperado, ordem.emitirOrdem());
    }
}
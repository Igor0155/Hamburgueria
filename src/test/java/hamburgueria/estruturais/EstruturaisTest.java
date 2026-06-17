package hamburgueria.estruturais;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class EstruturaisTest {

    @Test
    void deveFazerEntregaComBridge() {
        PedidoDelivery pedido = new PedidoApp(new TransporteMoto());
        assertEquals("Pedido via iFood/App. Entrega rápida de MOTO para: Rua X", pedido.processar("Rua X"));
    }

    @Test
    void deveAdaptarLogisticaTerceirizadaParaInterfaceDoSistema() {
        LogisticaTerceirizadaAPI apiExterna = new LogisticaTerceirizadaAPI();
        Transporte transporteAdapter = new TransporteTerceirizadoAdapter(apiExterna);

        String resultado = transporteAdapter.realizarEntrega("Avenida Central, 100");

        assertTrue(resultado.contains("Logística Externa enviou o pacote"));
        assertTrue(resultado.contains("para o destino: Avenida Central, 100"));
        assertTrue(resultado.contains("[TRK-"));
    }

    @Test
    void devePermitirAcessoParaGerente() {
        FuncionarioAcesso gerente = new FuncionarioAcesso("Carlos", "Gerente");
        Relatorio proxy = new RelatorioFinanceiroProxy(gerente);
        assertTrue(proxy.gerar().contains("Relatório Confidencial: Faturamento do mês"));
    }

    @Test
    void devePermitirAcessoParaDono() {
        FuncionarioAcesso dono = new FuncionarioAcesso("Ana", "Dono");
        Relatorio proxy = new RelatorioFinanceiroProxy(dono);
        assertTrue(proxy.gerar().contains("Relatório Confidencial: Faturamento do mês"));
    }

    @Test
    void deveNegarAcessoParaAtendente() {
        FuncionarioAcesso atendente = new FuncionarioAcesso("Marcos", "Atendente");
        Relatorio proxy = new RelatorioFinanceiroProxy(atendente);
        assertEquals("Acesso Negado: O cargo 'Atendente' não tem permissão para visualizar o financeiro.",
                proxy.gerar());
    }

    @Test
    void deveReaproveitarInstanciasFlyweight() {
        TipoIngrediente tipo1 = IngredienteFactory.getTipo("Cheddar", "Laticínios S/A", "Calorias: 100");
        TipoIngrediente tipo2 = IngredienteFactory.getTipo("Cheddar", "Laticínios S/A", "Calorias: 100");
        TipoIngrediente tipo3 = IngredienteFactory.getTipo("Bacon", "Frigorífico B", "Calorias: 250");

        assertSame(tipo1, tipo2);
        assertNotSame(tipo1, tipo3);
        assertEquals(2, IngredienteFactory.getTotalTiposCriados());
    }
}
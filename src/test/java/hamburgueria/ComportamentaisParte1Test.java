package hamburgueria.comportamentais;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComportamentaisParte1Test {

    @Test
    void deveCalcularFretesDiferentesComStrategy() {
        CalculadoraFrete calc = new CalculadoraFrete();

        assertEquals(10.0f, calc.calcularFrete(5.0f, new FreteFixo()));
        assertEquals(12.5f, calc.calcularFrete(5.0f, new FretePorKm())); // 5km * 2.5
        assertEquals(0.0f, calc.calcularFrete(5.0f, new FreteGratis()));
    }

    @Test
    void deveEscalarReclamacaoComChainOfResponsibility() {
        Atendente atendente = new Atendente();
        Gerente gerente = new Gerente();
        Dono dono = new Dono();

        // Monta a corrente: Atendente -> Gerente -> Dono
        atendente.setSuperior(gerente);
        gerente.setSuperior(dono);

        assertEquals("Atendente aprovou o desconto de R$ 10.0", atendente.processarReclamacao(10.0f));
        assertEquals("Gerente aprovou o desconto de R$ 40.0", atendente.processarReclamacao(40.0f));
        assertEquals("Dono da Hamburgueria aprovou o desconto de R$ 200.0", atendente.processarReclamacao(200.0f));
        assertEquals("Desconto negado. Nenhum cargo tem autorização para este valor.",
                atendente.processarReclamacao(1000.0f));
    }

    @Test
    void deveMudarEstadoENotificarPainelComStateEObserver() {
        PedidoFluxo pedido = new PedidoFluxo("Carlos");
        PainelCliente painel = new PainelCliente();

        pedido.addObserver(painel); // Painel começa a observar o pedido

        assertEquals("Novo", pedido.getNomeEstado());

        // Tenta finalizar direto (deve falhar por causa da regra do State)
        assertFalse(pedido.finalizar());

        // Fluxo correto
        assertTrue(pedido.preparar());
        assertEquals("PAINEL: Pedido de Carlos agora está: Preparando", painel.getUltimaMensagem());

        assertTrue(pedido.finalizar());
        assertEquals("PAINEL: Pedido de Carlos agora está: Pronto", painel.getUltimaMensagem());
    }
}
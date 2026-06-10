package hamburgueria.comportamentais;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ComportamentaisTest {

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

        assertFalse(pedido.finalizar());

        // Fluxo correto
        assertTrue(pedido.preparar());
        assertEquals("PAINEL: Pedido de Carlos agora está: Preparando", painel.getUltimaMensagem());

        assertTrue(pedido.finalizar());
        assertEquals("PAINEL: Pedido de Carlos agora está: Pronto", painel.getUltimaMensagem());
    }

    @Test
    void deveExecutarComandoDePreparo() {
        Cozinha cozinha = new Cozinha();
        TabletAtendente tablet = new TabletAtendente();
        Comando prepararSmash = new ComandoPrepararLanche(cozinha, "Smash Burger");

        String resultado = tablet.emitirComando(prepararSmash);

        assertEquals("Cozinha: Separando ingredientes e iniciando o preparo do Smash Burger", resultado);
    }

    @Test
    void deveDesfazerUltimoComando() {
        Cozinha cozinha = new Cozinha();
        TabletAtendente tablet = new TabletAtendente();
        Comando prepararBacon = new ComandoPrepararLanche(cozinha, "Bacon Supremo");

        // O garçom envia o comando
        tablet.emitirComando(prepararBacon);

        // O cliente desiste, o garçom cancela
        String resultadoCancelamento = tablet.cancelarUltimoComando();

        assertEquals("Cozinha: Abortando o preparo do Bacon Supremo e descartando itens na chapa",
                resultadoCancelamento);
    }

    @Test
    void deveAvisarQuandoNaoHouverComandosParaCancelar() {
        TabletAtendente tablet = new TabletAtendente();

        // Tenta cancelar sem ter feito nenhum pedido
        String resultado = tablet.cancelarUltimoComando();

        assertEquals("Tablet: Nenhum comando para cancelar.", resultado);
    }
}
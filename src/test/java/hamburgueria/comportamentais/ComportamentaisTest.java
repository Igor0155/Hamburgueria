package hamburgueria.comportamentais;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ComportamentaisTest {

    @Test
    void deveCalcularFretesDiferentesComStrategy() {
        CalculadoraFrete calc = new CalculadoraFrete();
        assertEquals(10.0f, calc.calcularFrete(5.0f, new FreteFixo()));
        assertEquals(12.5f, calc.calcularFrete(5.0f, new FretePorKm()));
        assertEquals(0.0f, calc.calcularFrete(5.0f, new FreteGratis()));
    }

    @Test
    void deveEscalarReclamacaoComChainOfResponsibility() {
        Atendente atendente = new Atendente();
        Gerente gerente = new Gerente();
        Dono dono = new Dono();

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
        pedido.addObserver(painel);

        assertEquals("Novo", pedido.getNomeEstado());
        assertFalse(pedido.finalizar());

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

        assertEquals("Cozinha: Separando ingredientes e iniciando o preparo do Smash Burger",
                tablet.emitirComando(prepararSmash));
    }

    @Test
    void deveDesfazerUltimoComando() {
        Cozinha cozinha = new Cozinha();
        TabletAtendente tablet = new TabletAtendente();
        Comando prepararBacon = new ComandoPrepararLanche(cozinha, "Bacon Supremo");

        tablet.emitirComando(prepararBacon);
        assertEquals("Cozinha: Abortando o preparo do Bacon Supremo e descartando itens na chapa",
                tablet.cancelarUltimoComando());
    }

    @Test
    void deveAvisarQuandoNaoHouverComandosParaCancelar() {
        TabletAtendente tablet = new TabletAtendente();
        assertEquals("Tablet: Nenhum comando para cancelar.", tablet.cancelarUltimoComando());
    }

    @Test
    void deveAprovarDescontoParaEstudanteNaTercaFeira() {
        ExpressaoPromocao regraFinal = new ExpressaoAnd(new ExpressaoEstudante(),
                new ExpressaoDiaSemana("Terça-feira"));
        assertTrue(regraFinal.interpretar(new ContextoPromocao("Terça-feira", true, false)));
        assertFalse(regraFinal.interpretar(new ContextoPromocao("Quarta-feira", true, false)));
    }

    @Test
    void deveAprovarDescontoVipOuTercaFeira() {
        ExpressaoPromocao regraFinal = new ExpressaoOr(new ExpressaoVip(), new ExpressaoDiaSemana("Terça-feira"));
        assertTrue(regraFinal.interpretar(new ContextoPromocao("Terça-feira", false, false)));
        assertTrue(regraFinal.interpretar(new ContextoPromocao("Sexta-feira", false, true)));
        assertFalse(regraFinal.interpretar(new ContextoPromocao("Sexta-feira", false, false)));
    }

    @Test
    void deveFecharCaixaComTemplateMethod() {
        ProcessamentoCaixa caixaTotem = new CaixaTotem();
        String resultado = caixaTotem.fecharCaixa();
        assertTrue(resultado.contains("Fechamento automático"));
        assertTrue(resultado.contains("Caixa Fechado no Sistema"));
    }

    @Test
    void deveEnviarMensagemComMediator() {
        CentralChat chat = new CentralChat();
        ClienteChat cliente = new ClienteChat(chat);
        EntregadorChat entregador = new EntregadorChat(chat);

        chat.setCliente(cliente);
        chat.setEntregador(entregador);

        cliente.enviar("Cheguei no portão.");
        assertEquals("Cheguei no portão.", entregador.getUltimaMensagemRecebida());
    }

    @Test
    void deveDesfazerCarrinhoComMemento() {
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarItem("X-Burguer");
        CarrinhoEstado salvo = carrinho.salvar();

        carrinho.adicionarItem("Batata Frita");
        assertEquals("X-Burguer, Batata Frita", carrinho.getConteudoAtual());

        carrinho.restaurar(salvo);
        assertEquals("X-Burguer", carrinho.getConteudoAtual());
    }

    @Test
    void deveCalcularCaloriasComVisitor() {
        Ingrediente p1 = new Pao(false);
        Ingrediente c1 = new Carne(150);
        CalculadoraCalorias calculadora = new CalculadoraCalorias();

        assertEquals(500, p1.aceitar(calculadora) + c1.aceitar(calculadora));
    }

    @Test
    void deveIterarPelosPedidosDaFila() {
        FilaPedidosCozinha fila = new FilaPedidosCozinha();
        fila.adicionarPedido("Pedido #101");
        fila.adicionarPedido("Pedido #102");

        PedidoIterator iterador = fila.criarIterador();

        assertTrue(iterador.hasNext());
        assertEquals("Pedido #101", iterador.next());
        assertTrue(iterador.hasNext());
        assertEquals("Pedido #102", iterador.next());
        assertFalse(iterador.hasNext());
        assertNull(iterador.next());
    }
}
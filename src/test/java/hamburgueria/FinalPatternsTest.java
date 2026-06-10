package hamburgueria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import hamburgueria.comportamentais.CaixaTotem;
import hamburgueria.comportamentais.CalculadoraCalorias;
import hamburgueria.comportamentais.Carne;
import hamburgueria.comportamentais.CarrinhoCompras;
import hamburgueria.comportamentais.CarrinhoEstado;
import hamburgueria.comportamentais.CentralChat;
import hamburgueria.comportamentais.ClienteChat;
import hamburgueria.comportamentais.EntregadorChat;
import hamburgueria.comportamentais.FilaPedidosCozinha;
import hamburgueria.comportamentais.Ingrediente;
import hamburgueria.comportamentais.Pao;
import hamburgueria.comportamentais.PedidoIterator;
import hamburgueria.comportamentais.ProcessamentoCaixa;
import hamburgueria.estruturais.FuncionarioAcesso;
import hamburgueria.estruturais.IngredienteFactory;
import hamburgueria.estruturais.LogisticaTerceirizadaAPI;
import hamburgueria.estruturais.PedidoApp;
import hamburgueria.estruturais.PedidoDelivery;
import hamburgueria.estruturais.Relatorio;
import hamburgueria.estruturais.RelatorioFinanceiroProxy;
import hamburgueria.estruturais.TipoIngrediente;
import hamburgueria.estruturais.Transporte;
import hamburgueria.estruturais.TransporteMoto;
import hamburgueria.estruturais.TransporteTerceirizadoAdapter;

class FinalPatternsTest {

    @Test
    void deveFazerEntregaComBridge() {
        PedidoDelivery pedido = new PedidoApp(new TransporteMoto());
        assertEquals("Pedido via iFood/App. Entrega rápida de MOTO para: Rua X", pedido.processar("Rua X"));
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
        CarrinhoEstado salvo = carrinho.salvar(); // Salva estado só com X-Burguer

        carrinho.adicionarItem("Batata Frita");
        assertEquals("X-Burguer, Batata Frita", carrinho.getConteudoAtual());

        carrinho.restaurar(salvo); // Desfaz a batata frita
        assertEquals("X-Burguer", carrinho.getConteudoAtual());
    }

    @Test
    void deveCalcularCaloriasComVisitor() {
        Ingrediente p1 = new Pao(false); // 200 cal
        Ingrediente c1 = new Carne(150); // 300 cal

        CalculadoraCalorias calculadora = new CalculadoraCalorias();

        int total = p1.aceitar(calculadora) + c1.aceitar(calculadora);
        assertEquals(500, total);
    }

    @Test
    void deveReaproveitarInstanciasFlyweight() {
        // Solicitamos o Cheddar duas vezes e o Bacon uma vez
        TipoIngrediente tipo1 = IngredienteFactory.getTipo("Cheddar", "Laticínios S/A", "Calorias: 100");
        TipoIngrediente tipo2 = IngredienteFactory.getTipo("Cheddar", "Laticínios S/A", "Calorias: 100");
        TipoIngrediente tipo3 = IngredienteFactory.getTipo("Bacon", "Frigorífico B", "Calorias: 250");

        assertSame(tipo1, tipo2);

        assertNotSame(tipo1, tipo3);

        assertEquals(2, IngredienteFactory.getTotalTiposCriados());
    }

    @Test
    void deveIterarPelosPedidosDaFila() {
        FilaPedidosCozinha fila = new FilaPedidosCozinha();
        fila.adicionarPedido("Pedido #101 - X-Burguer");
        fila.adicionarPedido("Pedido #102 - Combo Master");
        fila.adicionarPedido("Pedido #103 - Batata Frita");

        // Extrai o iterador da coleção
        PedidoIterator iterador = fila.criarIterador();

        // Navega sequencialmente usando hasNext() e next()
        assertTrue(iterador.hasNext());
        assertEquals("Pedido #101 - X-Burguer", iterador.next());

        assertTrue(iterador.hasNext());
        assertEquals("Pedido #102 - Combo Master", iterador.next());

        assertTrue(iterador.hasNext());
        assertEquals("Pedido #103 - Batata Frita", iterador.next());

        // No final da fila, hasNext deve ser falso
        assertFalse(iterador.hasNext());
        assertNull(iterador.next());
    }

    @Test
    void deveAdaptarLogisticaTerceirizadaParaInterfaceDoSistema() {
        // Instancia a API externa incompatível
        LogisticaTerceirizadaAPI apiExterna = new LogisticaTerceirizadaAPI();

        // Passa a API externa para dentro do nosso adaptador
        Transporte transporteAdapter = new TransporteTerceirizadoAdapter(apiExterna);

        // O nosso sistema aciona o método que ele já conhece: realizarEntrega()
        String resultado = transporteAdapter.realizarEntrega("Avenida Central, 100");

        // Valida se o adaptador traduziu a chamada com sucesso
        assertTrue(resultado.contains("Logística Externa enviou o pacote"));
        assertTrue(resultado.contains("para o destino: Avenida Central, 100"));
        assertTrue(resultado.contains("[TRK-")); // Verifica se o código de rastreio foi gerado
    }

    @Test
    void devePermitirAcessoParaGerente() {
        FuncionarioAcesso gerente = new FuncionarioAcesso("Carlos", "Gerente");
        Relatorio proxy = new RelatorioFinanceiroProxy(gerente);

        String resultado = proxy.gerar();

        assertTrue(resultado.contains("Relatório Confidencial: Faturamento do mês"));
    }

    @Test
    void devePermitirAcessoParaDono() {
        FuncionarioAcesso dono = new FuncionarioAcesso("Ana", "Dono");
        Relatorio proxy = new RelatorioFinanceiroProxy(dono);

        String resultado = proxy.gerar();

        assertTrue(resultado.contains("Relatório Confidencial: Faturamento do mês"));
    }

    @Test
    void deveNegarAcessoParaAtendente() {
        FuncionarioAcesso atendente = new FuncionarioAcesso("Marcos", "Atendente");
        Relatorio proxy = new RelatorioFinanceiroProxy(atendente);

        String resultado = proxy.gerar();

        assertEquals("Acesso Negado: O cargo 'Atendente' não tem permissão para visualizar o financeiro.", resultado);
    }
}
package hamburgueria;

import hamburgueria.estruturais.*;
import hamburgueria.comportamentais.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
package hamburgueria.comportamentais.mediator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;

class ClienteTest {

    private PedidoCliente pedidoReal;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(99)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(30.0f))
                .build();
    }

    @Test
    void deveElogiarAdministracaoViaMediator() {
        Cliente cliente = new Cliente();
        cliente.setPedido(pedidoReal);

        String esperado = "A Central de Atendimento agradece seu contato.\n" +
                "A Administração respondeu sua demanda conforme mensagem a seguir.\n" +
                ">>A Administração agradece o feedback para o pedido #99: Entrega super rápida";

        assertEquals(esperado, cliente.elogiarAdministracao("Entrega super rápida"));
    }

    @Test
    void deveReclamarAdministracaoViaMediator() {
        Cliente cliente = new Cliente();
        cliente.setPedido(pedidoReal);

        String esperado = "A Central de Atendimento agradece seu contato.\n" +
                "A Administração respondeu sua demanda conforme mensagem a seguir.\n" +
                ">>A Administração vai verificar o problem no pedido #99: Lanche veio frio";

        assertEquals(esperado, cliente.reclamarAdministracao("Lanche veio frio"));
    }

    @Test
    void deveSugerirAdministracaoViaMediator() {
        Cliente cliente = new Cliente();
        cliente.setPedido(pedidoReal);

        String esperado = "A Central de Atendimento agradece seu contato.\n" +
                "A Administração respondeu sua demanda conforme mensagem a seguir.\n" +
                ">>A Administração vai analisar a sugestão para o pedido #99: Adicionar pão de brioche no menu";

        assertEquals(esperado, cliente.sugerirAdministracao("Adicionar pão de brioche no menu"));
    }

    @Test
    void deveRetornarOPedidoAssociadoAoUsuario() {
        Cliente cliente = new Cliente();
        cliente.setPedido(pedidoReal);
        assertSame(pedidoReal, cliente.getPedido());
    }

    @Test
    void devePermitirAlterarOPedidoNoUsuario() {
        Cliente cliente = new Cliente();
        PedidoCliente novoPedido = new PedidoCliente();
        cliente.setPedido(novoPedido);
        assertSame(novoPedido, cliente.getPedido());
    }

    @Test
    void deveGarantirInstanciaUnicaDaCentralAtendimento() {
        assertSame(CentralAtendimento.getInstancia(), CentralAtendimento.getInstancia());
    }

    @Test
    void deveGarantirInstanciaUnicaDaAdministracao() {
        assertSame(AdministracaoHamburgueria.getInstancia(), AdministracaoHamburgueria.getInstancia());
    }

    @Test
    void deveRetornarMensagemCorretaDiretoNaAdministracaoElogio() {
        String resposta = AdministracaoHamburgueria.getInstancia().receberElogio("Parabéns", pedidoReal);
        assertEquals("A Administração agradece o feedback para o pedido #99: Parabéns", resposta);
    }

    @Test
    void deveRetornarMensagemCorretaDiretoNaAdministracaoReclamacao() {
        String resposta = AdministracaoHamburgueria.getInstancia().receberReclamacao("Erro", pedidoReal);
        assertEquals("A Administração vai verificar o problema no pedido #99: Erro", resposta);
    }

    @Test
    void deveRetornarMensagemCorretaDiretoNaAdministracaoSugestao() {
        String resposta = AdministracaoHamburgueria.getInstancia().receberSugestao("Ideia", pedidoReal);
        assertEquals("A Administração vai analisar a sugestão para o pedido #99: Ideia", resposta);
    }
}
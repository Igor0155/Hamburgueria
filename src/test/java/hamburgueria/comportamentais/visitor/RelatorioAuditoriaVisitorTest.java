package hamburgueria.comportamentais.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;
import hamburgueria.estruturais.decorator.HamburguerBase;
import hamburgueria.estruturais.flyweight.Receita;
import hamburgueria.estruturais.proxy.Funcionario;

class RelatorioAuditoriaVisitorTest {

    private PedidoCliente pedidoReal;
    private RelatorioAuditoriaVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new RelatorioAuditoriaVisitor();

        // INTEGRAÇÃO: Montando o Pedido no Builder para o Visitor consumir
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(1)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .setLanchePrincipal(new HamburguerBase(35.0f))
                .build();
    }

    @Test
    void deveExibirRelatorioDeAuditoriaDoPedidoCliente() {
        String esperado = "PedidoAuditoria{numero=1, cliente='Igor Gabriel', pagamento=Pagamento via PIX efetivado, lanche=Hambúrguer Base}";
        assertEquals(esperado, visitor.exibir(pedidoReal));
    }

    @Test
    void deveAcionarOVisitorDiretamentePeloMetodoAceitarDoPedido() {
        String esperado = "PedidoAuditoria{numero=1, cliente='Igor Gabriel', pagamento=Pagamento via PIX efetivado, lanche=Hambúrguer Base}";
        // Prova a injeção de dependência via interface
        assertEquals(esperado, pedidoReal.aceitar(visitor));
    }

    @Test
    void deveExibirRelatorioDeAuditoriaDoFuncionarioGerente() {
        Funcionario gerente = new Funcionario("Carlos", true);
        String esperado = "FuncionarioAuditoria{nome='Carlos', cargo=Gerente}";

        assertEquals(esperado, visitor.exibir(gerente));
    }

    @Test
    void deveAcionarOVisitorDiretamentePeloMetodoAceitarDoGerente() {
        Funcionario gerente = new Funcionario("Carlos", true);
        String esperado = "FuncionarioAuditoria{nome='Carlos', cargo=Gerente}";

        assertEquals(esperado, gerente.aceitar(visitor));
    }

    @Test
    void deveExibirRelatorioDeAuditoriaDoFuncionarioAtendente() {
        Funcionario atendente = new Funcionario("Jeanne", false);
        String esperado = "FuncionarioAuditoria{nome='Jeanne', cargo=Atendente}";

        assertEquals(esperado, visitor.exibir(atendente));
    }

    @Test
    void deveAcionarOVisitorDiretamentePeloMetodoAceitarDoAtendente() {
        Funcionario atendente = new Funcionario("Jeanne", false);
        String esperado = "FuncionarioAuditoria{nome='Jeanne', cargo=Atendente}";

        assertEquals(esperado, atendente.aceitar(visitor));
    }

    @Test
    void deveExibirRelatorioDeAuditoriaDaReceita() {
        Receita receita = new Receita("Smash Duplo", "Chapar 2 carnes", 10);
        String esperado = "ReceitaAuditoria{lanche='Smash Duplo', tempoMinutos=10}";

        assertEquals(esperado, visitor.exibir(receita));
    }

    @Test
    void deveAcionarOVisitorDiretamentePeloMetodoAceitarDaReceita() {
        Receita receita = new Receita("Smash Duplo", "Chapar 2 carnes", 10);
        String esperado = "ReceitaAuditoria{lanche='Smash Duplo', tempoMinutos=10}";

        assertEquals(esperado, receita.aceitar(visitor));
    }
}
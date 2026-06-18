package hamburgueria.comportamentais.chainofresponsibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.builder.PedidoClienteBuilder;
import hamburgueria.criacionais.factorymethod.PagamentoPix;
import hamburgueria.criacionais.singleton.ConfiguracaoRestaurante;

class MembroEquipeTest {

    private PedidoCliente pedidoReal;
    private MembroEquipe atendente;

    @BeforeEach
    void setUp() {
        ConfiguracaoRestaurante.getInstance().setAceitandoPedidos(true);
        pedidoReal = new PedidoClienteBuilder()
                .setNumeroPedido(1001)
                .setNomeCliente("Igor Gabriel")
                .setMetodoPagamento(new PagamentoPix())
                .build();

        // Montagem da Cadeia de Responsabilidade (De cima para baixo)
        SetorJuridico juridico = new SetorJuridico(null); // Topo
        GerenteFinanceiro financeiro = new GerenteFinanceiro(juridico);
        ChefeCozinha cozinha = new ChefeCozinha(financeiro);
        atendente = new AtendenteCaixa(cozinha); // Base
    }

    @Test
    void deveResolverDuvidaNoAtendente() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoDuvida.getInstancia(), pedidoReal);
        assertEquals("Atendente de Caixa", atendente.processarSolicitacao(solicitacao));
    }

    @Test
    void deveEscalarTrocaDeLancheParaChefeDeCozinha() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoTrocaLanche.getInstancia(), pedidoReal);
        assertEquals("Chefe de Cozinha", atendente.processarSolicitacao(solicitacao));
    }

    @Test
    void deveEscalarEstornoParaGerenteFinanceiro() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoEstorno.getInstancia(), pedidoReal);
        assertEquals("Gerente Financeiro", atendente.processarSolicitacao(solicitacao));
    }

    @Test
    void deveEscalarProcessoSanitarioParaSetorJuridico() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoJuridica.getInstancia(), pedidoReal);
        assertEquals("Setor Jurídico", atendente.processarSolicitacao(solicitacao));
    }

    @Test
    void deveRetornarSemResolucaoParaProblemaNaoMapeadoNaCadeia() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoNaoMapeada.getInstancia(), pedidoReal);
        assertEquals("Sem resolução", atendente.processarSolicitacao(solicitacao));
    }

    @Test
    void deveRetornarOTipoDeSolicitacaoCorreto() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoDuvida.getInstancia(), pedidoReal);
        assertSame(TipoSolicitacaoDuvida.getInstancia(), solicitacao.getTipoSolicitacao());
    }

    @Test
    void devePermitirAlterarOTipoDeSolicitacao() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoDuvida.getInstancia(), pedidoReal);
        solicitacao.setTipoSolicitacao(TipoSolicitacaoEstorno.getInstancia());
        assertSame(TipoSolicitacaoEstorno.getInstancia(), solicitacao.getTipoSolicitacao());
    }

    @Test
    void deveRetornarOPedidoRelacionadoASolicitacao() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoDuvida.getInstancia(), pedidoReal);
        assertSame(pedidoReal, solicitacao.getPedidoRelacionado());
    }

    @Test
    void devePermitirAlterarOPedidoRelacionado() {
        SolicitacaoSuporte solicitacao = new SolicitacaoSuporte(TipoSolicitacaoDuvida.getInstancia(), pedidoReal);
        PedidoCliente novoPedido = new PedidoCliente();
        solicitacao.setPedidoRelacionado(novoPedido);
        assertSame(novoPedido, solicitacao.getPedidoRelacionado());
    }
}
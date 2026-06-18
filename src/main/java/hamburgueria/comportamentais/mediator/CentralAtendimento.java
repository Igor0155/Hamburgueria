package hamburgueria.comportamentais.mediator;

import hamburgueria.criacionais.builder.PedidoCliente;

public class CentralAtendimento {

    private static CentralAtendimento instancia = new CentralAtendimento();

    private CentralAtendimento() {
    }

    public static CentralAtendimento getInstancia() {
        return instancia;
    }

    public String receberElogioAdministracao(String mensagem, PedidoCliente pedido) {
        return "A Central de Atendimento agradece seu contato.\n" +
                "A Administração respondeu sua demanda conforme mensagem a seguir.\n" +
                ">>" + AdministracaoHamburgueria.getInstancia().receberElogio(mensagem, pedido);
    }

    public String receberReclamacaoAdministracao(String mensagem, PedidoCliente pedido) {
        return "A Central de Atendimento agradece seu contato.\n" +
                "A Administração respondeu sua demanda conforme mensagem a seguir.\n" +
                ">>" + AdministracaoHamburgueria.getInstancia().receberReclamacao(mensagem, pedido);
    }

    public String receberSugestaoAdministracao(String mensagem, PedidoCliente pedido) {
        return "A Central de Atendimento agradece seu contato.\n" +
                "A Administração respondeu sua demanda conforme mensagem a seguir.\n" +
                ">>" + AdministracaoHamburgueria.getInstancia().receberSugestao(mensagem, pedido);
    }
}
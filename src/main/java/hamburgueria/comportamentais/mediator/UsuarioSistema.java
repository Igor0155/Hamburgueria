package hamburgueria.comportamentais.mediator;

import hamburgueria.criacionais.builder.PedidoCliente;

public class UsuarioSistema {

    private PedidoCliente pedido;

    public PedidoCliente getPedido() {
        return pedido;
    }

    public void setPedido(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public String elogiarAdministracao(String mensagem) {
        return CentralAtendimento.getInstancia().receberElogioAdministracao(mensagem, this.pedido);
    }

    public String reclamarAdministracao(String mensagem) {
        return CentralAtendimento.getInstancia().receberReclamacaoAdministracao(mensagem, this.pedido);
    }

    public String sugerirAdministracao(String mensagem) {
        return CentralAtendimento.getInstancia().receberSugestaoAdministracao(mensagem, this.pedido);
    }
}
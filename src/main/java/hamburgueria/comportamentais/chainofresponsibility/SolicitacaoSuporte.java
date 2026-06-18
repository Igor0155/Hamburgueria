package hamburgueria.comportamentais.chainofresponsibility;

import hamburgueria.criacionais.builder.PedidoCliente;

public class SolicitacaoSuporte {

    private TipoSolicitacao tipoSolicitacao;
    private PedidoCliente pedidoRelacionado;

    public SolicitacaoSuporte(TipoSolicitacao tipoSolicitacao, PedidoCliente pedidoRelacionado) {
        this.tipoSolicitacao = tipoSolicitacao;
        this.pedidoRelacionado = pedidoRelacionado;
    }

    public TipoSolicitacao getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public PedidoCliente getPedidoRelacionado() {
        return pedidoRelacionado;
    }

    public void setPedidoRelacionado(PedidoCliente pedidoRelacionado) {
        this.pedidoRelacionado = pedidoRelacionado;
    }
}
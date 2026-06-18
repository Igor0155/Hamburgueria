package hamburgueria.comportamentais.observer;

import java.util.Observable;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PedidoAcompanhamento extends Observable {

    private Integer idEtapa;
    private Integer sequencial;
    private String nomeEtapa;
    private PedidoCliente pedido;

    public PedidoAcompanhamento(Integer idEtapa, Integer sequencial, String nomeEtapa, PedidoCliente pedido) {
        this.idEtapa = idEtapa;
        this.sequencial = sequencial;
        this.nomeEtapa = nomeEtapa;
        this.pedido = pedido;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public String getNomeEtapa() {
        return nomeEtapa;
    }

    public void setNomeEtapa(String nomeEtapa) {
        this.nomeEtapa = nomeEtapa;
    }

    public PedidoCliente getPedido() {
        return pedido;
    }

    public void setPedido(PedidoCliente pedido) {
        this.pedido = pedido;
    }

    public void atualizarStatusFase() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Acompanhamento{" +
                "idEtapa=" + idEtapa +
                ", sequencial=" + sequencial +
                ", nomeEtapa='" + nomeEtapa + '\'' +
                ", pedidoId=" + pedido.getNumeroPedido() +
                '}';
    }
}
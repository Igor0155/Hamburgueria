package hamburgueria.comportamentais.observer;

import java.util.Observable;
import java.util.Observer;

public class NotificacaoCliente implements Observer {

    private String nome;
    private String ultimaNotificacao;

    public NotificacaoCliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimaNotificacao() {
        return this.ultimaNotificacao;
    }

    public void registrarNoAcompanhamento(PedidoAcompanhamento acompanhamento) {
        acompanhamento.addObserver(this);
    }

    @Override
    public void update(Observable acompanhamento, Object arg1) {
        this.ultimaNotificacao = this.nome + ", pedido atualizado no " + acompanhamento.toString();
    }
}
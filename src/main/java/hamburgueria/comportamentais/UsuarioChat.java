package hamburgueria.comportamentais;

public abstract class UsuarioChat {
    protected ChatMediator mediador;
    protected String ultimaMensagemRecebida;

    public UsuarioChat(ChatMediator mediador) {
        this.mediador = mediador;
    }

    public void enviar(String mensagem) {
        mediador.enviarMensagem(mensagem, this);
    }

    public void receberMensagem(String mensagem) {
        this.ultimaMensagemRecebida = mensagem;
    }

    public String getUltimaMensagemRecebida() {
        return this.ultimaMensagemRecebida;
    }
}
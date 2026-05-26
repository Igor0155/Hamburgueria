package hamburgueria.comportamentais;

public class CentralChat implements ChatMediator {
    private UsuarioChat cliente;
    private UsuarioChat entregador;

    public void setCliente(UsuarioChat cliente) {
        this.cliente = cliente;
    }

    public void setEntregador(UsuarioChat entregador) {
        this.entregador = entregador;
    }

    public void enviarMensagem(String mensagem, UsuarioChat remetente) {
        if (remetente == cliente && entregador != null) {
            entregador.receberMensagem(mensagem);
        } else if (remetente == entregador && cliente != null) {
            cliente.receberMensagem(mensagem);
        }
    }
}
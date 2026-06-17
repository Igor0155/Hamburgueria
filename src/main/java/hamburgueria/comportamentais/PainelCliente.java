package hamburgueria.comportamentais;

public class PainelCliente implements MonitorObserver {
    private String ultimaMensagem;

    @Override
    public void atualizar(String nomeCliente, String novoStatus) {
        this.ultimaMensagem = "PAINEL: Pedido de " + nomeCliente + " agora está: " + novoStatus;
    }

    @Override
    public String getUltimaMensagem() {
        return this.ultimaMensagem;
    }
}
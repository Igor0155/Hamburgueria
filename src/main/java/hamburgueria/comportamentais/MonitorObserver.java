package hamburgueria.comportamentais;

public interface MonitorObserver {
    void atualizar(String nomeCliente, String novoStatus);

    String getUltimaMensagem(); // Apenas para facilitar os testes
}
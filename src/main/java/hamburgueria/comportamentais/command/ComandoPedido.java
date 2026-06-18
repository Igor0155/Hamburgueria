package hamburgueria.comportamentais.command;

public interface ComandoPedido {
    void executar();

    void desfazer();
}
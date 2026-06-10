package hamburgueria.comportamentais;

public interface Comando {
    String executar();

    String desfazer();
}
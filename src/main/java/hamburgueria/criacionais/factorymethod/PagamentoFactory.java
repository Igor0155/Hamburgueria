package hamburgueria.criacionais.factorymethod;

public class PagamentoFactory {

    public static IPagamento obterPagamento(String pagamento) {
        Class<?> classe = null;
        Object objeto = null;
        try {
            classe = Class.forName("hamburgueria.criacionais.factorymethod.Pagamento" + pagamento);
            objeto = classe.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Pagamento inexistente");
        }
        if (!(objeto instanceof IPagamento)) {
            throw new IllegalArgumentException("Pagamento inválido");
        }
        return (IPagamento) objeto;
    }
}
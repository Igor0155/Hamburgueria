package hamburgueria.comportamentais.templatemethod;

public class ProcessadorFechamentoBalcao extends ProcessadorFechamento {

    @Override
    public String verificarStatusLiberacao() {
        // Balcão não tem valor mínimo, apenas exige que o pedido não esteja zerado
        if (this.calcularTotalBase() > 0.0f) {
            return "Liberado para Retirada";
        } else {
            return "Retido: Pedido Vazio";
        }
    }

    @Override
    public String getTipoCanal() {
        return "Balcão";
    }
}
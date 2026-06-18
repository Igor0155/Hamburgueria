package hamburgueria.comportamentais.templatemethod;

public class ProcessadorFechamentoDelivery extends ProcessadorFechamento {

    @Override
    public String verificarStatusLiberacao() {
        // Regra de Negócio: Delivery exige consumo mínimo de R$ 30,00
        if (this.calcularTotalBase() >= 30.0f) {
            return "Liberado para Rota";
        } else {
            return "Retido: Valor Mínimo Não Atingido";
        }
    }

    @Override
    public String getTipoCanal() {
        return "Delivery";
    }
}
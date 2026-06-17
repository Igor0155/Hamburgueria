package hamburgueria.estruturais;

public class RelatorioFinanceiroReal implements Relatorio {

    private String dadosProcessados;

    public RelatorioFinanceiroReal() {
        this.dadosProcessados = consolidarDadosNoBanco();
    }

    private String consolidarDadosNoBanco() {
        double faturamento = 150000.00;
        double despesas = 105000.00;
        double lucro = faturamento - despesas;

        return "Faturamento do mês = R$ " + faturamento + ". Lucro = R$ " + lucro + ".";
    }

    @Override
    public String gerar() {
        return "Relatório Confidencial: " + this.dadosProcessados;
    }
}
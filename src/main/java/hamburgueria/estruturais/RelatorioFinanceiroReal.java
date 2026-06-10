package hamburgueria.estruturais;

public class RelatorioFinanceiroReal implements Relatorio {

    public RelatorioFinanceiroReal() {
        // Simula um processamento pesado no banco de dados para puxar as vendas
        System.out.println("Processando dados pesados do banco de dados financeiro...");
    }

    @Override
    public String gerar() {
        return "Relatório Confidencial: Faturamento do mês = R$ 150.000,00. Lucro = R$ 45.000,00.";
    }
}
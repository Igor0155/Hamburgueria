package hamburgueria.estruturais;

public class RelatorioFinanceiroProxy implements Relatorio {

    private RelatorioFinanceiroReal relatorioReal;
    private FuncionarioAcesso funcionarioLogado;

    public RelatorioFinanceiroProxy(FuncionarioAcesso funcionario) {
        this.funcionarioLogado = funcionario;
    }

    @Override
    public String gerar() {
        // 1. Controle de Acesso
        if (temPermissao()) {

            if (relatorioReal == null) {
                relatorioReal = new RelatorioFinanceiroReal();
            }
            return relatorioReal.gerar();
        } else {
            return "Acesso Negado: O cargo '" + funcionarioLogado.getCargo()
                    + "' não tem permissão para visualizar o financeiro.";
        }
    }

    private boolean temPermissao() {
        String cargo = funcionarioLogado.getCargo();
        return cargo.equalsIgnoreCase("Gerente") || cargo.equalsIgnoreCase("Dono");
    }
}
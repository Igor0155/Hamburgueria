package hamburgueria.estruturais.proxy;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.factorymethod.IPagamento;

public class RegistroPedidoProxy implements IRegistroPedido {

    private RegistroPedidoReal registroReal;
    private Integer numeroPedido;

    public RegistroPedidoProxy(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    @Override
    public PedidoCliente obterDadosEntrega() {
        if (this.registroReal == null) {
            this.registroReal = new RegistroPedidoReal(this.numeroPedido);
        }
        return this.registroReal.obterDadosEntrega();
    }

    @Override
    public IPagamento obterMetodoPagamento(Funcionario funcionario) {
        if (!funcionario.isGerente()) {
            throw new IllegalArgumentException("Acesso Negado: Apenas gerentes podem ver os dados de pagamento.");
        }
        if (this.registroReal == null) {
            this.registroReal = new RegistroPedidoReal(this.numeroPedido);
        }
        return this.registroReal.obterMetodoPagamento(funcionario);
    }

    @Override
    public Float obterFaturamentoLiquido(Funcionario funcionario) {
        if (!funcionario.isGerente()) {
            throw new IllegalArgumentException("Acesso Negado: Apenas gerentes podem ver o faturamento líquido.");
        }
        if (this.registroReal == null) {
            this.registroReal = new RegistroPedidoReal(this.numeroPedido);
        }
        return this.registroReal.obterFaturamentoLiquido(funcionario);
    }
}
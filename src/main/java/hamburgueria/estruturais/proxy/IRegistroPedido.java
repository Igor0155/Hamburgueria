package hamburgueria.estruturais.proxy;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.factorymethod.IPagamento;

public interface IRegistroPedido {
    // Acesso Público (Qualquer um pode ver o que entregar)
    PedidoCliente obterDadosEntrega();

    // Acesso Protegido (Só a gerência pode acessar a interface de pagamento e os
    // lucros)
    IPagamento obterMetodoPagamento(Funcionario funcionario);

    Float obterFaturamentoLiquido(Funcionario funcionario);
}
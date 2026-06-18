package hamburgueria.comportamentais.visitor;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.estruturais.flyweight.Receita;
import hamburgueria.estruturais.proxy.Funcionario;

public interface VisitorAuditoria {
    String exibirPedido(PedidoCliente pedido);

    String exibirFuncionario(Funcionario funcionario);

    String exibirReceita(Receita receita);
}
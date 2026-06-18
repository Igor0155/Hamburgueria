package hamburgueria.comportamentais.visitor;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.estruturais.flyweight.Receita;
import hamburgueria.estruturais.proxy.Funcionario;

public class RelatorioAuditoriaVisitor implements VisitorAuditoria {

    public String exibir(IElementoAuditoria elemento) {
        return elemento.aceitar(this);
    }

    @Override
    public String exibirPedido(PedidoCliente pedido) {
        // INTEGRAÇÃO: O Visitor lê os dados formatados do Factory Method e do Decorator
        return "PedidoAuditoria{" +
                "numero=" + pedido.getNumeroPedido() +
                ", cliente='" + pedido.getNomeCliente() + '\'' +
                ", pagamento=" + pedido.getMetodoPagamento().processar() +
                ", lanche=" + pedido.getLanchePrincipal().getDescricao() +
                '}';
    }

    @Override
    public String exibirFuncionario(Funcionario funcionario) {
        return "FuncionarioAuditoria{" +
                "nome='" + funcionario.getNome() + '\'' +
                ", cargo=" + (funcionario.isGerente() ? "Gerente" : "Atendente") +
                '}';
    }

    @Override
    public String exibirReceita(Receita receita) {
        return "ReceitaAuditoria{" +
                "lanche='" + receita.getNomeLanche() + '\'' +
                ", tempoMinutos=" + receita.getTempoEstimadoMinutos() +
                '}';
    }
}
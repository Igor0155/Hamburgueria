package hamburgueria.estruturais.proxy;

import hamburgueria.criacionais.builder.PedidoCliente;
import hamburgueria.criacionais.factorymethod.IPagamento;

public class RegistroPedidoReal implements IRegistroPedido {

    private PedidoCliente pedido;

    public RegistroPedidoReal(int numeroPedido) {
        PedidoCliente pedidoSalvo = BancoDeDadosPedidos.getPedido(numeroPedido);
        if (pedidoSalvo == null) {
            throw new IllegalArgumentException("Pedido não encontrado no banco de dados");
        }
        this.pedido = pedidoSalvo; // Simula a carga pesada
    }

    @Override
    public PedidoCliente obterDadosEntrega() {
        return this.pedido;
    }

    @Override
    public IPagamento obterMetodoPagamento(Funcionario funcionario) {
        return this.pedido.getMetodoPagamento();
    }

    @Override
    public Float obterFaturamentoLiquido(Funcionario funcionario) {
        // Usa a interface do Decorator (Lanche) para fazer cálculos matemáticos
        // integrados
        float valorBruto = this.pedido.getLanchePrincipal().getPreco();
        float custosIngredientes = valorBruto * 0.40f; // 40% de custo fictício
        return valorBruto - custosIngredientes;
    }
}
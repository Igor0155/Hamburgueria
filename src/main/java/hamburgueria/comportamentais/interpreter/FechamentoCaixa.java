package hamburgueria.comportamentais.interpreter;

import hamburgueria.criacionais.builder.PedidoCliente;

public class FechamentoCaixa {

    private PedidoCliente pedido;

    public FechamentoCaixa(PedidoCliente pedido) {
        if (pedido == null || pedido.getLanchePrincipal() == null) {
            throw new IllegalArgumentException("Pedido inválido para fechamento de caixa");
        }
        this.pedido = pedido;
    }

    public PedidoCliente getPedido() {
        return this.pedido;
    }

    // Método funcional: Aplica uma regra em texto (que poderia vir do Banco de
    // Dados)
    public double cobrarComCupomDinamico(String regraCupom, double taxaFrete) {
        return MotorCalculoDinamico.calcularValorComRegraDinamica(this.pedido, regraCupom, taxaFrete);
    }
}
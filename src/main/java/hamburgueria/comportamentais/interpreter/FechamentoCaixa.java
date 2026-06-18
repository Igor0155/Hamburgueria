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

    public double processarPagamentoFinal(double taxaFrete) {
        // Regra padrão se o cliente não tiver cupom
        String regraAplicada = "lanchePreco + taxaFrete";

        // Se o Builder injetou um cupom real, nós extraímos a fórmula dele
        if (this.pedido.getCupomDesconto() != null) {
            regraAplicada = this.pedido.getCupomDesconto().getRegraMatematica();
        }

        return MotorCalculoDinamico.calcularValorComRegraDinamica(this.pedido, regraAplicada, taxaFrete);
    }
}
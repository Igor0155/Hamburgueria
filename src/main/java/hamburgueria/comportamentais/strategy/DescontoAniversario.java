package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class DescontoAniversario implements EstrategiaCalculo {
    public float calcular(PedidoCliente pedido) {
        // 20% de desconto
        return pedido.getLanchePrincipal().getPreco() * 0.80f;
    }
}
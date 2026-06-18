package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class DescontoPrimeiraCompra implements EstrategiaCalculo {
    public float calcular(PedidoCliente pedido) {
        // 15% de desconto
        return pedido.getLanchePrincipal().getPreco() * 0.85f;
    }
}
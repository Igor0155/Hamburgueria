package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class PrecoNormal implements EstrategiaCalculo {
    public float calcular(PedidoCliente pedido) {
        return pedido.getLanchePrincipal().getPreco();
    }
}
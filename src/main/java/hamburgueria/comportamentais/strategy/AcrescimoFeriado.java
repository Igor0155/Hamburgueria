package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class AcrescimoFeriado implements EstrategiaCalculo {
    public float calcular(PedidoCliente pedido) {
        // Acréscimo de 10%
        return pedido.getLanchePrincipal().getPreco() * 1.10f;
    }
}
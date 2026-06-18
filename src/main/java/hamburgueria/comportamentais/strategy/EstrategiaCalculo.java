package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public interface EstrategiaCalculo {
    float calcular(PedidoCliente pedido);
}
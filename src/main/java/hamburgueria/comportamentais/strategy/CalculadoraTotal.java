package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class CalculadoraTotal {

    private PedidoCliente pedido;

    public CalculadoraTotal(PedidoCliente pedido) {
        if (pedido == null || pedido.getLanchePrincipal() == null) {
            throw new IllegalArgumentException("Pedido inválido para cálculo");
        }
        this.pedido = pedido;
    }

    public float calcular(EstrategiaCalculo estrategia) {
        return estrategia.calcular(this.pedido);
    }
}
package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class TaxaMadrugada implements EstrategiaCalculo {
    public float calcular(PedidoCliente pedido) {
        // Acréscimo de 20% por serviço noturno
        return pedido.getLanchePrincipal().getPreco() * 1.20f;
    }
}
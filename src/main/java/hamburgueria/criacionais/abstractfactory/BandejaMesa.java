package hamburgueria.criacionais.abstractfactory;

public class BandejaMesa implements TransportePedido {
    public String despachar() {
        return "Pedido entregue na Bandeja de Mesa";
    }
}
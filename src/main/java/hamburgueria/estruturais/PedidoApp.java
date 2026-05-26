package hamburgueria.estruturais;

public class PedidoApp extends PedidoDelivery {
    public PedidoApp(Transporte transporte) {
        super(transporte);
    }

    public String processar(String endereco) {
        return "Pedido via iFood/App. " + transporte.realizarEntrega(endereco);
    }
}
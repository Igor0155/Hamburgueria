package hamburgueria.criacionais.abstractfactory;

public class Pedido {

    private RecipienteLanche recipienteLanche;
    private RecipienteBebida recipienteBebida;
    private TransportePedido transportePedido;

    public Pedido(FabricaAbstrata fabrica) {
        this.recipienteLanche = fabrica.createRecipienteLanche();
        this.recipienteBebida = fabrica.createRecipienteBebida();
        this.transportePedido = fabrica.createTransportePedido();
    }

    public String embalarLanche() {
        return this.recipienteLanche.embalar();
    }

    public String servirBebida() {
        return this.recipienteBebida.servir();
    }

    public String despacharPedido() {
        return this.transportePedido.despachar();
    }
}
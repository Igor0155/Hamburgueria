package hamburgueria.estruturais;

public abstract class PedidoDelivery {
    protected Transporte transporte; // A PONTE (Bridge)

    public PedidoDelivery(Transporte transporte) {
        this.transporte = transporte;
    }

    public abstract String processar(String endereco);
}
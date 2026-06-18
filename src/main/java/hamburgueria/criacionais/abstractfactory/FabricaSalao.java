package hamburgueria.criacionais.abstractfactory;

public class FabricaSalao implements FabricaAbstrata {
    @Override
    public RecipienteLanche createRecipienteLanche() {
        return new CestaMadeira();
    }

    @Override
    public RecipienteBebida createRecipienteBebida() {
        return new CopoVidro();
    }

    @Override
    public TransportePedido createTransportePedido() {
        return new BandejaMesa();
    }
}
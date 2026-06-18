package hamburgueria.criacionais.abstractfactory;

public class FabricaDelivery implements FabricaAbstrata {
    @Override
    public RecipienteLanche createRecipienteLanche() {
        return new CaixaTermica();
    }

    @Override
    public RecipienteBebida createRecipienteBebida() {
        return new CopoSelado();
    }

    @Override
    public TransportePedido createTransportePedido() {
        return new SacolaComLacre();
    }
}
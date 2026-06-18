package hamburgueria.criacionais.abstractfactory;

public class FabricaDriveThru implements FabricaAbstrata {
    @Override
    public RecipienteLanche createRecipienteLanche() {
        return new CaixaPapelao();
    }

    @Override
    public RecipienteBebida createRecipienteBebida() {
        return new CopoPlasticoComTampa();
    }

    @Override
    public TransportePedido createTransportePedido() {
        return new SacolaPapelKraft();
    }
}
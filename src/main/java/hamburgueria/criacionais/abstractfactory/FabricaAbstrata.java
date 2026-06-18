package hamburgueria.criacionais.abstractfactory;

public interface FabricaAbstrata {
    RecipienteLanche createRecipienteLanche();

    RecipienteBebida createRecipienteBebida();

    TransportePedido createTransportePedido();
}
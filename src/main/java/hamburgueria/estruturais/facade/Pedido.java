package hamburgueria.estruturais.facade;

public class Pedido {

    public boolean despachar() {
        return PedidoFacade.verificarPendenciasLiberacao(this);
    }
}
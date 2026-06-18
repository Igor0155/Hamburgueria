package hamburgueria.comportamentais.memento;

import hamburgueria.comportamentais.state.PedidoEstado;

public class PedidoMemento {

    private PedidoEstado estadoSalvo;

    public PedidoMemento(PedidoEstado estadoSalvo) {
        this.estadoSalvo = estadoSalvo;
    }

    public PedidoEstado getEstadoSalvo() {
        return estadoSalvo;
    }
}
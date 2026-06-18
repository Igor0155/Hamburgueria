package hamburgueria.comportamentais.memento;

import hamburgueria.criacionais.builder.PedidoCliente;
import java.util.ArrayList;
import java.util.List;

public class ZeladorHistoricoPedido {

    private PedidoCliente pedido;
    private List<PedidoMemento> historicoMementos = new ArrayList<>();

    public ZeladorHistoricoPedido(PedidoCliente pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O zelador precisa de um pedido válido para monitorar");
        }
        this.pedido = pedido;
    }

    public void salvarEstadoAtual() {
        this.historicoMementos.add(this.pedido.criarMemento());
    }

    public void restaurarEstado(int indice) {
        if (indice < 0 || indice >= this.historicoMementos.size()) {
            throw new IllegalArgumentException("Índice de histórico inválido");
        }
        PedidoMemento mementoRecuperado = this.historicoMementos.get(indice);
        this.pedido.restaurarMemento(mementoRecuperado);
    }

    public int getTotalEstadosSalvos() {
        return this.historicoMementos.size();
    }

    public PedidoMemento getMemento(int indice) {
        if (indice < 0 || indice >= this.historicoMementos.size()) {
            throw new IllegalArgumentException("Índice de histórico inválido");
        }
        return this.historicoMementos.get(indice);
    }
}
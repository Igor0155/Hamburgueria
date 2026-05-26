package hamburgueria.comportamentais;

import java.util.ArrayList;
import java.util.List;

public class PedidoFluxo {
    private String nomeCliente;
    private EstadoPedido estado;

    // Lista de observadores (Monitores, Painéis)
    private List<MonitorObserver> observadores = new ArrayList<>();

    public PedidoFluxo(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.estado = new EstadoNovo(); // Todo pedido nasce como "Novo"
    }

    public void addObserver(MonitorObserver observer) {
        this.observadores.add(observer);
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        // Sempre que o estado mudar, avisa os painéis! (Padrão Observer)
        for (MonitorObserver obs : observadores) {
            obs.atualizar(this.nomeCliente, this.estado.getNomeEstado());
        }
    }

    public String getNomeEstado() {
        return this.estado.getNomeEstado();
    }

    // Delega as ações para a máquina de estados
    public boolean preparar() {
        return estado.preparar(this);
    }

    public boolean finalizar() {
        return estado.finalizar(this);
    }

    public boolean entregar() {
        return estado.entregar(this);
    }
}
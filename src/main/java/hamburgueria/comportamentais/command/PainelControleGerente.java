package hamburgueria.comportamentais.command;

import java.util.ArrayList;
import java.util.List;

public class PainelControleGerente {

    private List<ComandoPedido> historicoComandos = new ArrayList<ComandoPedido>();

    public void executarComando(ComandoPedido comando) {
        this.historicoComandos.add(comando);
        comando.executar();
    }

    public void desfazerUltimoComando() {
        if (!this.historicoComandos.isEmpty()) {
            ComandoPedido comando = this.historicoComandos.get(this.historicoComandos.size() - 1);
            comando.desfazer();
            this.historicoComandos.remove(this.historicoComandos.size() - 1);
        }
    }

    public int getQuantidadeComandosNoHistorico() {
        return this.historicoComandos.size();
    }
}
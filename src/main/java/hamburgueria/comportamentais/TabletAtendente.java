package hamburgueria.comportamentais;

import java.util.ArrayList;
import java.util.List;

public class TabletAtendente {

    // Histórico de comandos para permitir o "Desfazer"
    private List<Comando> historicoComandos = new ArrayList<>();

    public String emitirComando(Comando comando) {
        this.historicoComandos.add(comando);
        return comando.executar();
    }

    public String cancelarUltimoComando() {
        if (historicoComandos.isEmpty()) {
            return "Tablet: Nenhum comando para cancelar.";
        }

        // Remove o último comando da lista e aciona o desfazer
        Comando ultimoComando = historicoComandos.remove(historicoComandos.size() - 1);
        return ultimoComando.desfazer();
    }
}
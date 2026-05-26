package hamburgueria.estruturais;

import java.util.HashMap;
import java.util.Map;

public class IngredienteFactory {
    private static Map<String, TipoIngrediente> tipos = new HashMap<>();

    public static TipoIngrediente getTipo(String nome, String fornecedor, String dados) {
        // Se o tipo já existe, reaproveita. Se não, cria e guarda no mapa.
        if (!tipos.containsKey(nome)) {
            tipos.put(nome, new TipoIngrediente(nome, fornecedor, dados));
        }
        return tipos.get(nome);
    }

    public static int getTotalTiposCriados() {
        return tipos.size(); // Para provar a economia de memória nos testes
    }
}
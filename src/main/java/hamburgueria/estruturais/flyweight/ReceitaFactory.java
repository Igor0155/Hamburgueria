package hamburgueria.estruturais.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ReceitaFactory {

    private static Map<String, Receita> receitasCache = new HashMap<>();

    public static Receita getReceita(String nomeLanche, String modoDePreparo, int tempoEstimadoMinutos) {
        Receita receita = receitasCache.get(nomeLanche);
        if (receita == null) {
            receita = new Receita(nomeLanche, modoDePreparo, tempoEstimadoMinutos);
            receitasCache.put(nomeLanche, receita);
        }
        return receita;
    }

    public static int getTotalReceitasEmMemoria() {
        return receitasCache.size();
    }

    // Método obrigatório para garantir o isolamento dos testes do JUnit
    public static void limparCache() {
        receitasCache.clear();
    }
}
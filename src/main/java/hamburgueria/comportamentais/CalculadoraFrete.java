package hamburgueria.comportamentais;

public class CalculadoraFrete {
    public float calcularFrete(float distancia, EstrategiaFrete estrategia) {
        return estrategia.calcular(distancia);
    }
}
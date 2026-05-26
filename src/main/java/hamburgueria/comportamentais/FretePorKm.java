package hamburgueria.comportamentais;

public class FretePorKm implements EstrategiaFrete {
    public float calcular(float distanciaEmKm) {
        return distanciaEmKm * 2.5f; // R$ 2,50 por KM rodado
    }
}
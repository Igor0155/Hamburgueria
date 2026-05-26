package hamburgueria.comportamentais;

public class FreteGratis implements EstrategiaFrete {
    public float calcular(float distanciaEmKm) {
        return 0.0f; // Frete promocional (Zera o frete)
    }
}
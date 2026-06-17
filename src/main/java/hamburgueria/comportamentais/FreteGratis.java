package hamburgueria.comportamentais;

public class FreteGratis implements EstrategiaFrete {

    @Override
    public float calcular(float distanciaEmKm) {
        return 0.0f; // Frete promocional (Zera o frete)
    }
}
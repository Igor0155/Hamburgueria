package hamburgueria.comportamentais;

public class FreteFixo implements EstrategiaFrete {

    @Override
    public float calcular(float distanciaEmKm) {
        return 10.0f; // Qualquer distância custa 10 reais
    }
}
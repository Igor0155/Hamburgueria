package hamburgueria.comportamentais;

public class CalculadoraCalorias implements VisitorNutricional {
    public int calcularCaloriasPao(Pao pao) {
        return pao.isIntegral() ? 120 : 200; // Pão integral é mais leve
    }

    public int calcularCaloriasCarne(Carne carne) {
        return carne.getGramas() * 2; // 2 calorias por grama de carne
    }
}
package hamburgueria.comportamentais;

public interface VisitorNutricional {
    int calcularCaloriasPao(Pao pao);

    int calcularCaloriasCarne(Carne carne);
}
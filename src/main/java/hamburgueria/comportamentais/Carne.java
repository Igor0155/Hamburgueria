package hamburgueria.comportamentais;

public class Carne implements Ingrediente {
    private int gramas;

    public Carne(int gramas) {
        this.gramas = gramas;
    }

    public int getGramas() {
        return gramas;
    }

    public int aceitar(VisitorNutricional visitor) {
        return visitor.calcularCaloriasCarne(this);
    }
}
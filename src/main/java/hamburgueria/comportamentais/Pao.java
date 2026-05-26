package hamburgueria.comportamentais;

public class Pao implements Ingrediente {
    private boolean integral;

    public Pao(boolean integral) {
        this.integral = integral;
    }

    public boolean isIntegral() {
        return integral;
    }

    public int aceitar(VisitorNutricional visitor) {
        return visitor.calcularCaloriasPao(this);
    }
}
package hamburgueria.comportamentais.interpreter;

public class Divisao implements InterpretadorExpressao {

    private double x;
    private double y;

    public Divisao(Numero elementoEsquerda, Numero elementoDireita) {
        x = elementoEsquerda.getNumero();
        y = elementoDireita.getNumero();
    }

    public double interpretar() {
        if (y == 0) {
            throw new IllegalArgumentException("Divisão por zero");
        }
        return x / y;
    }
}
package hamburgueria.estruturais.facade;

public class Cozinha extends Departamento {

    private static Cozinha cozinha = new Cozinha();

    private Cozinha() {
    };

    public static Cozinha getInstancia() {
        return cozinha;
    }
}
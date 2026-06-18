package hamburgueria.estruturais.facade;

public class Logistica extends Departamento {

    private static Logistica logistica = new Logistica();

    private Logistica() {
    };

    public static Logistica getInstancia() {
        return logistica;
    }
}
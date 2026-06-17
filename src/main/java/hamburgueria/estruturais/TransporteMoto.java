package hamburgueria.estruturais;

public class TransporteMoto implements Transporte {
    @Override
    public String realizarEntrega(String endereco) {
        return "Entrega rápida de MOTO para: " + endereco;
    }
}
package hamburgueria.estruturais;

public class TransporteBicicleta implements Transporte {
    @Override
    public String realizarEntrega(String endereco) {
        return "Entrega ecológica de BICICLETA para: " + endereco;
    }
}
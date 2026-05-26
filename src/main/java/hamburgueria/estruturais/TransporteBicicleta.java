package hamburgueria.estruturais;

public class TransporteBicicleta implements Transporte {
    public String realizarEntrega(String endereco) {
        return "Entrega ecológica de BICICLETA para: " + endereco;
    }
}
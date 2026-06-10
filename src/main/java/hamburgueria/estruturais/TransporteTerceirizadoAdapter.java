package hamburgueria.estruturais;

public class TransporteTerceirizadoAdapter implements Transporte {

    private LogisticaTerceirizadaAPI apiExterna;

    public TransporteTerceirizadoAdapter(LogisticaTerceirizadaAPI apiExterna) {
        this.apiExterna = apiExterna;
    }

    @Override
    public String realizarEntrega(String endereco) {
        // O sistema manda apenas o endereço. O Adapter traduz isso para a API externa,
        // gerando o código de rastreio que ela exige para funcionar.
        String codigoGerado = "TRK-" + System.currentTimeMillis();

        return this.apiExterna.enviarPacote(endereco, codigoGerado);
    }
}
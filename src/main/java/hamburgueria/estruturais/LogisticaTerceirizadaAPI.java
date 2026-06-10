package hamburgueria.estruturais;

public class LogisticaTerceirizadaAPI {

    // Método com assinatura incompatível com a nossa interface Transporte
    public String enviarPacote(String destino, String codigoRastreio) {
        return "Logística Externa enviou o pacote [" + codigoRastreio + "] para o destino: " + destino;
    }
}
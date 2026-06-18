package hamburgueria.criacionais.singleton;

public class ConfiguracaoRestaurante {

    private static ConfiguracaoRestaurante instance = new ConfiguracaoRestaurante();

    private ConfiguracaoRestaurante() {

        if (instance != null) {
            throw new IllegalStateException("Instância Singleton já existe. Use o método getInstance().");
        }
    }

    public static ConfiguracaoRestaurante getInstance() {
        return instance;
    }

    private String nomeRestaurante;
    private String cnpj;
    private boolean aceitandoPedidos;
    private float taxaServicoPadrao;
    private int limitePedidosPorHora;
    private String chavePixPrincipal;
    private String telefoneSuporte;
    private String enderecoMatriz;

    // Getters e Setters
    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isAceitandoPedidos() {
        return aceitandoPedidos;
    }

    public void setAceitandoPedidos(boolean aceitandoPedidos) {
        this.aceitandoPedidos = aceitandoPedidos;
    }

    public float getTaxaServicoPadrao() {
        return taxaServicoPadrao;
    }

    public void setTaxaServicoPadrao(float taxaServicoPadrao) {
        this.taxaServicoPadrao = taxaServicoPadrao;
    }

    public int getLimitePedidosPorHora() {
        return limitePedidosPorHora;
    }

    public void setLimitePedidosPorHora(int limitePedidosPorHora) {
        this.limitePedidosPorHora = limitePedidosPorHora;
    }

    public String getChavePixPrincipal() {
        return chavePixPrincipal;
    }

    public void setChavePixPrincipal(String chavePixPrincipal) {
        this.chavePixPrincipal = chavePixPrincipal;
    }

    public String getTelefoneSuporte() {
        return telefoneSuporte;
    }

    public void setTelefoneSuporte(String telefoneSuporte) {
        this.telefoneSuporte = telefoneSuporte;
    }

    public String getEnderecoMatriz() {
        return enderecoMatriz;
    }

    public void setEnderecoMatriz(String enderecoMatriz) {
        this.enderecoMatriz = enderecoMatriz;
    }
}
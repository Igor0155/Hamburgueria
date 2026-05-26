package hamburgueria.criacionais;

public class ConfiguracaoRestaurante {
    private static ConfiguracaoRestaurante instancia;

    private String nomeRestaurante;
    private String cnpj;
    private boolean aberto;

    // Construtor privado para impedir 'new' externo
    private ConfiguracaoRestaurante() {
        this.nomeRestaurante = "Burger Design Patterns";
        this.cnpj = "12.345.678/0001-99";
        this.aberto = false;
    }

    public static ConfiguracaoRestaurante getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracaoRestaurante();
        }
        return instancia;
    }

    public void abrirRestaurante() {
        this.aberto = true;
    }

    public void fecharRestaurante() {
        this.aberto = false;
    }

    public boolean isAberto() {
        return aberto;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }
}
package hamburgueria.criacionais;

public class FabricaSalao implements EmbalagemFactory {
    public CaixaLanche criarCaixa() {
        return new BandejaSalao();
    }

    public CopoBebida criarCopo() {
        return new CopoVidroSalao();
    }
}
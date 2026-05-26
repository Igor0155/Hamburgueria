package hamburgueria.criacionais;

public class FabricaDelivery implements EmbalagemFactory {
    public CaixaLanche criarCaixa() {
        return new CaixaTermicaDelivery();
    }

    public CopoBebida criarCopo() {
        return new CopoSeladoDelivery();
    }
}
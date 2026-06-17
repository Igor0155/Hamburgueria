package hamburgueria.criacionais;

public class FabricaDelivery implements EmbalagemFactory {
    @Override
    public CaixaLanche criarCaixa() {
        return new CaixaTermicaDelivery();
    }

    @Override
    public CopoBebida criarCopo() {
        return new CopoSeladoDelivery();
    }
}
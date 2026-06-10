package hamburgueria.comportamentais;

public class ComandoPrepararLanche implements Comando {

    private Cozinha cozinha;
    private String lanche;

    public ComandoPrepararLanche(Cozinha cozinha, String lanche) {
        this.cozinha = cozinha;
        this.lanche = lanche;
    }

    @Override
    public String executar() {
        return cozinha.iniciarPreparo(this.lanche);
    }

    @Override
    public String desfazer() {
        return cozinha.abortarPreparo(this.lanche);
    }
}
package hamburgueria.criacionais;

public class CopoVidroSalao implements CopoBebida {
    @Override
    public String servir() {
        return "Bebida no Copo de Vidro (Retornável)";
    }
}
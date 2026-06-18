package hamburgueria.comportamentais.interpreter;

public class CupomDesconto {

    private String codigo;
    private String regraMatematica;

    public CupomDesconto(String codigo, String regraMatematica) {
        this.codigo = codigo;
        this.regraMatematica = regraMatematica;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRegraMatematica() {
        return regraMatematica;
    }
}
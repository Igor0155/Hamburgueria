package hamburgueria.estruturais.decorator;

public abstract class LancheDecorator implements Lanche {

    private Lanche lanche;
    public String descricaoEstrutura;

    public LancheDecorator(Lanche lanche) {
        this.lanche = lanche;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public abstract float getPrecoAdicional();

    public float getPreco() {
        return this.lanche.getPreco() + this.getPrecoAdicional();
    }

    public abstract String getNomeAdicional();

    public String getDescricao() {
        return this.lanche.getDescricao() + " + " + this.getNomeAdicional();
    }

    public void setDescricaoEstrutura(String descricaoEstrutura) {
        this.descricaoEstrutura = descricaoEstrutura;
    }
}
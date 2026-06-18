package hamburgueria.estruturais.decorator;

public abstract class LancheDecorator implements Lanche {
    private Lanche lanche;

    public LancheDecorator(Lanche lanche) {
        this.lanche = lanche;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public abstract float getPrecoAdicional();

    public abstract String getNomeAdicional();

    public float getPreco() {
        return this.lanche.getPreco() + this.getPrecoAdicional();
    }

    public String getDescricao() {
        return this.lanche.getDescricao() + " + " + this.getNomeAdicional();
    }

    @Override
    public Lanche clone() throws CloneNotSupportedException {
        LancheDecorator lancheClone = (LancheDecorator) super.clone();
        lancheClone.lanche = this.lanche.clone(); // Deep Clone (recursividade do Prototype)
        return lancheClone;
    }
}
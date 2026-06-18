package hamburgueria.estruturais.composite;

public abstract class ItemCardapio {

    private String descricao;

    public ItemCardapio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public abstract String getDetalhes();
}
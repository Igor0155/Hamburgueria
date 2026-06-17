package hamburgueria;

public class Produto implements ComponenteCardapio {
    private String nome;
    private float preco;

    public Produto(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String getDescricao() {
        return this.nome;
    }

    @Override
    public float getPreco() {
        return this.preco;
    }
}
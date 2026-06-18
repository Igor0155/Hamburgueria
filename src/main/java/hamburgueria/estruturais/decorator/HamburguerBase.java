package hamburgueria.estruturais.decorator;

public class HamburguerBase implements Lanche {

    public float preco;

    public HamburguerBase() {
    }

    public HamburguerBase(float preco) {
        this.preco = preco;
    }

    public float getPreco() {
        return preco;
    }

    public String getDescricao() {
        return "Hambúrguer Base";
    }
}
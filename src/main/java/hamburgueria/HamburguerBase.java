package hamburgueria;

public class HamburguerBase implements ComponenteCardapio {
    public String getDescricao() {
        return "Hambúrguer (Pão Artesanal e Burger 180g)";
    }

    public float getPreco() {
        return 22.0f;
    }
}
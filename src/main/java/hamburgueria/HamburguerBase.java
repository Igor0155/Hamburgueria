package hamburgueria;

public class HamburguerBase implements ComponenteCardapio {
    @Override
    public String getDescricao() {
        return "Hambúrguer (Pão Artesanal e Burger 180g)";
    }

    @Override
    public float getPreco() {
        return 22.0f;
    }
}
package hamburgueria;

public class ComboFacade {

    // A Fachada esconde a complexidade de instanciar Decorators e Composites
    public static ComponenteCardapio montarComboMaster() {
        Categoria combo = new Categoria("Combo Master");

        // 1. Monta o Hambúrguer Decorado
        ComponenteCardapio lanche = new HamburguerBase();
        lanche = new AdicionalCheddar(lanche);
        lanche = new AdicionalBacon(lanche);
        lanche = new AdicionalOnionRings(lanche); // 22 + 5 + 7 + 6 = 40.0

        // 2. Separa os Produtos Folhas (Composite)
        ComponenteCardapio batata = new Produto("Batata Frita Média", 12.0f);
        ComponenteCardapio refri = new Produto("Refrigerante 500ml", 8.0f);

        // 3. Agrupa tudo no Composite principal
        combo.adicionar(lanche);
        combo.adicionar(batata);
        combo.adicionar(refri); // Total do combo: 40 + 12 + 8 = 60.0

        return combo;
    }
}
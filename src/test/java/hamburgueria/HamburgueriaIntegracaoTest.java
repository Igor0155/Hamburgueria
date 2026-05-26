package hamburgueria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HamburgueriaIntegracaoTest {

    @Test
    void deveRetornarPrecoDescricaoProdutoFolha() {
        ComponenteCardapio batata = new Produto("Batata Frita", 15.0f);
        assertEquals(15.0f, batata.getPreco(), 0.01f);
        assertEquals("Batata Frita", batata.getDescricao());
    }

    @Test
    void deveRetornarPrecoDescricaoHamburguerDecorado() {
        ComponenteCardapio lanche = new HamburguerBase();
        lanche = new AdicionalCheddar(lanche);
        lanche = new AdicionalBacon(lanche);

        // Base(22) + Cheddar(5) + Bacon(7) = 34.0
        assertEquals(34.0f, lanche.getPreco(), 0.01f);
        assertEquals("Hambúrguer (Pão Artesanal e Burger 180g) + Cheddar Cremoso + Bacon Crocante",
                lanche.getDescricao());
    }

    @Test
    void deveRetornarPrecoDescricaoComboViaFacade() {
        // O cliente só chama 1 linha de código!
        ComponenteCardapio meuCombo = ComboFacade.montarComboMaster();

        // Verifica se a Facade somou corretamente os Decorators (40) + Folhas (20)
        assertEquals(60.0f, meuCombo.getPreco(), 0.01f);

        // Verifica se a árvore do Composite iterou corretamente pelas strings
        String descricaoEsperada = "Combo Master contendo: [" +
                "Hambúrguer (Pão Artesanal e Burger 180g) + Cheddar Cremoso + Bacon Crocante + Onion Rings, " +
                "Batata Frita Média, " +
                "Refrigerante 500ml]";

        assertEquals(descricaoEsperada, meuCombo.getDescricao());
    }
}
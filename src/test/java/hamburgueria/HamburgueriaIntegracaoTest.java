package hamburgueria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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

        assertEquals(34.0f, lanche.getPreco(), 0.01f);
        assertEquals("Hambúrguer (Pão Artesanal e Burger 180g) + Cheddar Cremoso + Bacon Crocante",
                lanche.getDescricao());
    }

    @Test
    void deveRetornarPrecoDescricaoComboViaFacade() {
        ComponenteCardapio meuCombo = ComboFacade.montarComboMaster();
        assertEquals(60.0f, meuCombo.getPreco(), 0.01f);

        String descricaoEsperada = "Combo Master contendo: [" +
                "Hambúrguer (Pão Artesanal e Burger 180g) + Cheddar Cremoso + Bacon Crocante + Onion Rings, " +
                "Batata Frita Média, " +
                "Refrigerante 500ml]";

        assertEquals(descricaoEsperada, meuCombo.getDescricao());
    }

    @Test
    void deveRetornarPrecoZeroParaCategoriaVazia() {
        Categoria comboVazio = new Categoria("Combo Teste");
        assertEquals(0.0f, comboVazio.getPreco(), 0.01f);
        assertEquals("Combo Teste contendo: []", comboVazio.getDescricao());
    }
}
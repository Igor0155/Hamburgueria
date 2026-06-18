package hamburgueria.estruturais.composite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class PedidoTest {

    @Test
    void deveRetornarDescricaoItemIndividual() {
        ItemIndividual item = new ItemIndividual("Batata Frita", 15);
        assertEquals("Batata Frita", item.getDescricao());
    }

    @Test
    void deveAlterarDescricaoItemIndividual() {
        ItemIndividual item = new ItemIndividual("Batata Frita", 15);
        item.setDescricao("Batata Frita Rústica");
        assertEquals("Batata Frita Rústica", item.getDescricao());
    }

    @Test
    void deveRetornarPrecoItemIndividual() {
        ItemIndividual item = new ItemIndividual("Batata Frita", 15);
        assertEquals(15, item.getPreco());
    }

    @Test
    void deveAlterarPrecoItemIndividual() {
        ItemIndividual item = new ItemIndividual("Batata Frita", 15);
        item.setPreco(18);
        assertEquals(18, item.getPreco());
    }

    @Test
    void deveRetornarDetalhesItemIndividual() {
        ItemIndividual item = new ItemIndividual("Cheeseburger", 25);
        assertEquals("Item: Cheeseburger - preço: R$ 25\n", item.getDetalhes());
    }

    @Test
    void deveRetornarDescricaoCategoria() {
        CategoriaCardapio categoria = new CategoriaCardapio("Bebidas");
        assertEquals("Bebidas", categoria.getDescricao());
    }

    @Test
    void deveAlterarDescricaoCategoria() {
        CategoriaCardapio categoria = new CategoriaCardapio("Bebidas");
        categoria.setDescricao("Bebidas Alcoólicas");
        assertEquals("Bebidas Alcoólicas", categoria.getDescricao());
    }

    @Test
    void deveAdicionarItemNaCategoria() {
        CategoriaCardapio categoria = new CategoriaCardapio("Sucos");
        ItemIndividual suco = new ItemIndividual("Suco de Uva", 10);
        categoria.addItem(suco);
        assertEquals(1, categoria.getConteudos().size());
    }

    @Test
    void deveRetornarDetalhesCategoriaComUmItem() {
        CategoriaCardapio categoria = new CategoriaCardapio("Bebidas");
        ItemIndividual agua = new ItemIndividual("Água Sem Gás", 5);
        categoria.addItem(agua);

        assertEquals("Categoria: Bebidas\nItem: Água Sem Gás - preço: R$ 5\n", categoria.getDetalhes());
    }

    @Test
    void deveRetornarDetalhesCategoriaComVariosItens() {
        CategoriaCardapio categoria = new CategoriaCardapio("Sobremesas");
        ItemIndividual sorvete = new ItemIndividual("Sorvete", 12);
        ItemIndividual pudim = new ItemIndividual("Pudim", 15);

        categoria.addItem(sorvete);
        categoria.addItem(pudim);

        assertEquals("Categoria: Sobremesas\nItem: Sorvete - preço: R$ 12\nItem: Pudim - preço: R$ 15\n",
                categoria.getDetalhes());
    }

    @Test
    void deveRetornarDetalhesCategoriaComSubcategoriaVazia() {
        CategoriaCardapio menuPrincipal = new CategoriaCardapio("Menu Principal");
        CategoriaCardapio menuKids = new CategoriaCardapio("Menu Kids");

        menuPrincipal.addItem(menuKids);

        assertEquals("Categoria: Menu Principal\nCategoria: Menu Kids\n", menuPrincipal.getDetalhes());
    }

    @Test
    void deveRetornarItensObjetoPedido() {
        Pedido pedido = new Pedido();
        ItemIndividual item = new ItemIndividual("Combo Solteiro", 45);
        pedido.setItens(item);
        assertSame(item, pedido.getItensObjeto());
    }

    @Test
    void deveRetornarDetalhesPedidoComItemIndividual() {
        Pedido pedido = new Pedido();
        ItemIndividual avulso = new ItemIndividual("Refrigerante", 8);
        pedido.setItens(avulso);

        assertEquals("Item: Refrigerante - preço: R$ 8\n", pedido.getItens());
    }

    @Test
    void deveRetornarDetalhesPedidoComCategoria() {
        Pedido pedido = new Pedido();
        CategoriaCardapio categoria = new CategoriaCardapio("Lanches");
        ItemIndividual burger = new ItemIndividual("Smash Burger", 20);

        categoria.addItem(burger);
        pedido.setItens(categoria);

        assertEquals("Categoria: Lanches\nItem: Smash Burger - preço: R$ 20\n", pedido.getItens());
    }

    @Test
    void deveRetornarExcecaoPedidoSemItens() {
        try {
            Pedido pedido = new Pedido();
            pedido.getItens();
            fail();
        } catch (NullPointerException e) {
            assertEquals("Pedido sem itens", e.getMessage());
        }
    }
}
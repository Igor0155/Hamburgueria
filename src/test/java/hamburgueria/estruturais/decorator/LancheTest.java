package hamburgueria.estruturais.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.Test;

class LancheTest {

    @Test
    void deveRetornarPrecoLancheBase() {
        Lanche lanche = new HamburguerBase(20.0f);
        assertEquals(20.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarPrecoLancheComBacon() {
        Lanche lanche = new AdicionalBacon(new HamburguerBase(20.0f));
        assertEquals(25.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarPrecoLancheComCheddar() {
        Lanche lanche = new AdicionalCheddar(new HamburguerBase(20.0f));
        assertEquals(24.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarPrecoLancheComCebolaCaramelizada() {
        Lanche lanche = new AdicionalCebolaCaramelizada(new HamburguerBase(20.0f));
        assertEquals(23.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarDescricaoLancheBase() {
        Lanche lanche = new HamburguerBase(20.0f);
        assertEquals("Hambúrguer Base", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComBaconECheddar() {
        Lanche lanche = new AdicionalBacon(new AdicionalCheddar(new HamburguerBase(20.0f)));
        assertEquals("Hambúrguer Base + Cheddar + Bacon", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComTodosOsAdicionais() {
        Lanche lanche = new AdicionalBacon(
                new AdicionalCheddar(new AdicionalCebolaCaramelizada(new HamburguerBase(20.0f))));
        assertEquals("Hambúrguer Base + Cebola Caramelizada + Cheddar + Bacon", lanche.getDescricao());
    }

    // TESTE DO PROTOTYPE INTEGRADO AO DECORATOR
    @Test
    void deveClonarLancheComDeepCloneMantendoIntegridade() throws CloneNotSupportedException {
        Lanche lancheOriginal = new AdicionalBacon(new AdicionalCheddar(new HamburguerBase(20.0f)));
        Lanche lancheClonado = lancheOriginal.clone();

        // O clone deve ter os mesmos dados, mas ser um objeto diferente na memória!
        assertEquals(lancheOriginal.getDescricao(), lancheClonado.getDescricao());
        assertEquals(lancheOriginal.getPreco(), lancheClonado.getPreco());
        assertNotSame(lancheOriginal, lancheClonado);
    }
}
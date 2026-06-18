package hamburgueria.estruturais.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
    void deveRetornarPrecoLancheComBaconECheddar() {
        Lanche lanche = new AdicionalBacon(new AdicionalCheddar(new HamburguerBase(20.0f)));
        assertEquals(29.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarPrecoLancheComBaconECebolaCaramelizada() {
        Lanche lanche = new AdicionalBacon(new AdicionalCebolaCaramelizada(new HamburguerBase(20.0f)));
        assertEquals(28.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarPrecoLancheComCheddarECebolaCaramelizada() {
        Lanche lanche = new AdicionalCheddar(new AdicionalCebolaCaramelizada(new HamburguerBase(20.0f)));
        assertEquals(27.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarPrecoLancheComTodosOsAdicionais() {
        Lanche lanche = new AdicionalBacon(
                new AdicionalCheddar(new AdicionalCebolaCaramelizada(new HamburguerBase(20.0f))));
        assertEquals(32.0f, lanche.getPreco());
    }

    @Test
    void deveRetornarDescricaoLancheBase() {
        Lanche lanche = new HamburguerBase();
        assertEquals("Hambúrguer Base", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComBacon() {
        Lanche lanche = new AdicionalBacon(new HamburguerBase());
        assertEquals("Hambúrguer Base + Bacon", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComCheddar() {
        Lanche lanche = new AdicionalCheddar(new HamburguerBase());
        assertEquals("Hambúrguer Base + Cheddar", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComCebolaCaramelizada() {
        Lanche lanche = new AdicionalCebolaCaramelizada(new HamburguerBase());
        assertEquals("Hambúrguer Base + Cebola Caramelizada", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComBaconECheddar() {
        Lanche lanche = new AdicionalBacon(new AdicionalCheddar(new HamburguerBase()));
        assertEquals("Hambúrguer Base + Cheddar + Bacon", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComBaconECebolaCaramelizada() {
        Lanche lanche = new AdicionalBacon(new AdicionalCebolaCaramelizada(new HamburguerBase()));
        assertEquals("Hambúrguer Base + Cebola Caramelizada + Bacon", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComCheddarECebolaCaramelizada() {
        Lanche lanche = new AdicionalCheddar(new AdicionalCebolaCaramelizada(new HamburguerBase()));
        assertEquals("Hambúrguer Base + Cebola Caramelizada + Cheddar", lanche.getDescricao());
    }

    @Test
    void deveRetornarDescricaoLancheComTodosOsAdicionais() {
        Lanche lanche = new AdicionalBacon(new AdicionalCheddar(new AdicionalCebolaCaramelizada(new HamburguerBase())));
        assertEquals("Hambúrguer Base + Cebola Caramelizada + Cheddar + Bacon", lanche.getDescricao());
    }

    @Test
    void devePermitirTrocarInstanciaDoLancheInterno() {
        LancheDecorator decorator = new AdicionalBacon(new HamburguerBase(20.0f));
        Lanche lancheMaior = new HamburguerBase(30.0f);

        decorator.setLanche(lancheMaior);

        assertSame(lancheMaior, decorator.getLanche());
        assertEquals(35.0f, decorator.getPreco());
    }

    @Test
    void devePermitirAlterarAtributoInternoDoDecorator() {
        LancheDecorator decorator = new AdicionalCheddar(new HamburguerBase());
        decorator.setDescricaoEstrutura("Promocao do Dia");

        assertEquals("Promocao do Dia", decorator.descricaoEstrutura);
    }
}
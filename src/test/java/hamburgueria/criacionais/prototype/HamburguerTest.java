package hamburgueria.criacionais.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class HamburguerTest {

    @Test
    void deveRetornarIdHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        assertEquals(1, hamburguer.getId());
    }

    @Test
    void deveAlterarIdHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        hamburguer.setId(2);
        assertEquals(2, hamburguer.getId());
    }

    @Test
    void deveRetornarNomeHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        assertEquals("Smash", hamburguer.getNome());
    }

    @Test
    void deveAlterarNomeHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        hamburguer.setNome("Double Smash");
        assertEquals("Double Smash", hamburguer.getNome());
    }

    @Test
    void deveRetornarTipoCarneHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        assertEquals("Bovino", hamburguer.getTipoCarne());
    }

    @Test
    void deveAlterarTipoCarneHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        hamburguer.setTipoCarne("Frango");
        assertEquals("Frango", hamburguer.getTipoCarne());
    }

    @Test
    void deveRetornarFornecedorHamburguer() {
        Fornecedor fornecedor = new Fornecedor("Carne S/A", 10);
        Hamburguer hamburguer = new Hamburguer(1, "Smash", fornecedor, "Bovino");
        assertSame(fornecedor, hamburguer.getFornecedor());
    }

    @Test
    void deveAlterarFornecedorHamburguer() {
        Hamburguer hamburguer = new Hamburguer(1, "Smash", new Fornecedor("Carne S/A", 10), "Bovino");
        Fornecedor novoFornecedor = new Fornecedor("Distribuidora B", 20);
        hamburguer.setFornecedor(novoFornecedor);
        assertSame(novoFornecedor, hamburguer.getFornecedor());
    }

    @Test
    void deveRetornarNomeEmpresaFornecedor() {
        Fornecedor fornecedor = new Fornecedor("Carne S/A", 10);
        assertEquals("Carne S/A", fornecedor.getNomeEmpresa());
    }

    @Test
    void deveAlterarNomeEmpresaFornecedor() {
        Fornecedor fornecedor = new Fornecedor("Carne S/A", 10);
        fornecedor.setNomeEmpresa("Frigorífico Central");
        assertEquals("Frigorífico Central", fornecedor.getNomeEmpresa());
    }

    @Test
    void deveRetornarCodigoFornecedor() {
        Fornecedor fornecedor = new Fornecedor("Carne S/A", 10);
        assertEquals(10, fornecedor.getCodigo());
    }

    @Test
    void deveAlterarCodigoFornecedor() {
        Fornecedor fornecedor = new Fornecedor("Carne S/A", 10);
        fornecedor.setCodigo(15);
        assertEquals(15, fornecedor.getCodigo());
    }

    @Test
    void deveRetornarToStringFornecedor() {
        Fornecedor fornecedor = new Fornecedor("Carne S/A", 10);
        assertEquals("Fornecedor{nomeEmpresa='Carne S/A', codigo=10}", fornecedor.toString());
    }

    @Test
    void deveGarantirCloneProfundoEToStringsCorretos() throws CloneNotSupportedException {
        Hamburguer hamburguer = new Hamburguer(101, "Burger Original", new Fornecedor("Frigo JF", 44), "Picanha");

        Hamburguer hamburguerClone = hamburguer.clone();
        hamburguerClone.setId(202);
        hamburguerClone.setNome("Burger Clonado");
        hamburguerClone.getFornecedor().setCodigo(55);

        assertEquals(
                "Hamburguer{id=101, nome='Burger Original', fornecedor=Fornecedor{nomeEmpresa='Frigo JF', codigo=44}, tipoCarne='Picanha'}",
                hamburguer.toString());
        assertEquals(
                "Hamburguer{id=202, nome='Burger Clonado', fornecedor=Fornecedor{nomeEmpresa='Frigo JF', codigo=55}, tipoCarne='Picanha'}",
                hamburguerClone.toString());
    }
}
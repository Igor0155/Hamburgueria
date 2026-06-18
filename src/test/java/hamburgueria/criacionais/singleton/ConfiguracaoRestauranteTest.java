package hamburgueria.criacionais.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ConfiguracaoRestauranteTest {

    @Test
    public void deveGarantirQueInstanciasSaoAMesmaReferenciaNaMemoria() {
        ConfiguracaoRestaurante instancia1 = ConfiguracaoRestaurante.getInstance();
        ConfiguracaoRestaurante instancia2 = ConfiguracaoRestaurante.getInstance();

        assertSame(instancia1, instancia2, "As instâncias devem apontar para o mesmo endereço de memória.");
    }

    @Test
    public void deveGarantirCompartilhamentoDeEstadoEntreInstancias() {
        ConfiguracaoRestaurante instancia1 = ConfiguracaoRestaurante.getInstance();
        ConfiguracaoRestaurante instancia2 = ConfiguracaoRestaurante.getInstance();

        instancia1.setNomeRestaurante("Hamburgueria UFJF");

        // Se eu altero na instancia 1, a instancia 2 TEM que refletir a mudança
        assertEquals("Hamburgueria UFJF", instancia2.getNomeRestaurante());
    }

    @Test
    public void deveImpedirNovaInstanciacaoViaReflection() {
        Exception excecao = assertThrows(InvocationTargetException.class, () -> {
            // Tenta acessar o construtor privado de forma "ilegal" e forçar a criação
            Constructor<ConfiguracaoRestaurante> construtor = ConfiguracaoRestaurante.class.getDeclaredConstructor();
            construtor.setAccessible(true);
            construtor.newInstance();
        });

        // Verifica se a nossa trava de segurança interna funcionou
        assertTrue(excecao.getCause() instanceof IllegalStateException);
        assertEquals("Instância Singleton já existe. Use o método getInstance().", excecao.getCause().getMessage());
    }

    @Test
    public void deveArmazenarERetornarNomeRestaurante() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setNomeRestaurante("Burger do Igor");
        assertEquals("Burger do Igor", config.getNomeRestaurante());
    }

    @Test
    public void deveArmazenarERetornarCnpj() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setCnpj("11.222.333/0001-44");
        assertEquals("11.222.333/0001-44", config.getCnpj());
    }

    @Test
    public void deveArmazenarERetornarStatusAceitandoPedidos() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setAceitandoPedidos(false);
        assertFalse(config.isAceitandoPedidos());
    }

    @Test
    public void deveArmazenarERetornarTaxaDeServico() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setTaxaServicoPadrao(12.5f);
        assertEquals(12.5f, config.getTaxaServicoPadrao(), 0.001f); // 0.001f é o delta de tolerância para Float
    }

    @Test
    public void deveArmazenarERetornarLimitePedidosPorHora() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setLimitePedidosPorHora(200);
        assertEquals(200, config.getLimitePedidosPorHora());
    }

    @Test
    public void deveArmazenarERetornarChavePix() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setChavePixPrincipal("CNPJ-11222333000144");
        assertEquals("CNPJ-11222333000144", config.getChavePixPrincipal());
    }

    @Test
    public void deveArmazenarERetornarTelefone() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setTelefoneSuporte("32 98888-7777");
        assertEquals("32 98888-7777", config.getTelefoneSuporte());
    }

    @Test
    public void deveArmazenarERetornarEnderecoMatriz() {
        ConfiguracaoRestaurante config = ConfiguracaoRestaurante.getInstance();
        config.setEnderecoMatriz("Rua Halfeld, Centro, Juiz de Fora");
        assertEquals("Rua Halfeld, Centro, Juiz de Fora", config.getEnderecoMatriz());
    }
}
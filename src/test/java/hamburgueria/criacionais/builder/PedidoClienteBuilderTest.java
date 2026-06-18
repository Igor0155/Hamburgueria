package hamburgueria.criacionais.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class PedidoClienteBuilderTest {

    @Test
    void deveRetornarExcecaoParaPedidoSemNumero() {
        try {
            PedidoClienteBuilder builder = new PedidoClienteBuilder();
            PedidoCliente pedido = builder
                    .setNomeCliente("Igor Gabriel")
                    .setLanchePrincipal("Smash Burger")
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Número do pedido inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaPedidoSemNomeCliente() {
        try {
            PedidoClienteBuilder builder = new PedidoClienteBuilder();
            PedidoCliente pedido = builder
                    .setNumeroPedido(101)
                    .setLanchePrincipal("Smash Burger")
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nome do cliente inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarPedidoValido() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder
                .setNumeroPedido(101)
                .setNomeCliente("Igor Gabriel")
                .build();

        assertNotNull(pedido);
    }

    @Test
    void deveConstruirPedidoComNumero() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").build();
        assertEquals(101, pedido.getNumeroPedido());
    }

    @Test
    void deveConstruirPedidoComNomeCliente() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").build();
        assertEquals("Igor", pedido.getNomeCliente());
    }

    @Test
    void deveConstruirPedidoComCpf() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setCpfCliente("111.222.333-44")
                .build();
        assertEquals("111.222.333-44", pedido.getCpfCliente());
    }

    @Test
    void deveConstruirPedidoComTelefone() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setTelefone("32 99999-9999").build();
        assertEquals("32 99999-9999", pedido.getTelefone());
    }

    @Test
    void deveConstruirPedidoComEmail() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setEmail("igor@email.com").build();
        assertEquals("igor@email.com", pedido.getEmail());
    }

    @Test
    void deveConstruirPedidoComEnderecoLogradouro() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setEnderecoLogradouro("Rua Central")
                .build();
        assertEquals("Rua Central", pedido.getEnderecoLogradouro());
    }

    @Test
    void deveConstruirPedidoComEnderecoNumero() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setEnderecoNumero(150).build();
        assertEquals(150, pedido.getEnderecoNumero());
    }

    @Test
    void deveConstruirPedidoComEnderecoComplemento() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setEnderecoComplemento("Apto 202")
                .build();
        assertEquals("Apto 202", pedido.getEnderecoComplemento());
    }

    @Test
    void deveConstruirPedidoComEnderecoBairro() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setEnderecoBairro("Centro").build();
        assertEquals("Centro", pedido.getEnderecoBairro());
    }

    @Test
    void deveConstruirPedidoComEnderecoCidade() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setEnderecoCidade("Juiz de Fora")
                .build();
        assertEquals("Juiz de Fora", pedido.getEnderecoCidade());
    }

    @Test
    void deveConstruirPedidoComCep() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setCep("36000-000").build();
        assertEquals("36000-000", pedido.getCep());
    }

    @Test
    void deveConstruirPedidoComLanchePrincipal() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setLanchePrincipal("Double Bacon")
                .build();
        assertEquals("Double Bacon", pedido.getLanchePrincipal());
    }

    @Test
    void deveConstruirPedidoComAdicionais() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setAdicionais("Cheddar Extra")
                .build();
        assertEquals("Cheddar Extra", pedido.getAdicionais());
    }

    @Test
    void deveConstruirPedidoComBebida() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setBebida("Refrigerante Cola")
                .build();
        assertEquals("Refrigerante Cola", pedido.getBebida());
    }

    @Test
    void deveConstruirPedidoComSobremesa() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setSobremesa("Sorvete Casquinha")
                .build();
        assertEquals("Sorvete Casquinha", pedido.getSobremesa());
    }

    @Test
    void deveConstruirPedidoComMetodoPagamento() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor")
                .setMetodoPagamento("Cartão de Crédito").build();
        assertEquals("Cartão de Crédito", pedido.getMetodoPagamento());
    }

    @Test
    void deveConstruirPedidoComObservacoes() {
        PedidoClienteBuilder builder = new PedidoClienteBuilder();
        PedidoCliente pedido = builder.setNumeroPedido(101).setNomeCliente("Igor").setObservacoes("Sem cebola e tomate")
                .build();
        assertEquals("Sem cebola e tomate", pedido.getObservacoes());
    }
}
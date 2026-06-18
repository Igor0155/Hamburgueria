package hamburgueria.criacionais.builder;

public class PedidoClienteBuilder {

    private PedidoCliente pedido;

    public PedidoClienteBuilder() {
        pedido = new PedidoCliente();
    }

    public PedidoCliente build() {
        if (pedido.getNumeroPedido() == 0) {
            throw new IllegalArgumentException("Número do pedido inválido");
        }
        if (pedido.getNomeCliente().equals("")) {
            throw new IllegalArgumentException("Nome do cliente inválido");
        }
        return pedido;
    }

    public PedidoClienteBuilder setNumeroPedido(int numeroPedido) {
        pedido.setNumeroPedido(numeroPedido);
        return this;
    }

    public PedidoClienteBuilder setNomeCliente(String nomeCliente) {
        pedido.setNomeCliente(nomeCliente);
        return this;
    }

    public PedidoClienteBuilder setCpfCliente(String cpfCliente) {
        pedido.setCpfCliente(cpfCliente);
        return this;
    }

    public PedidoClienteBuilder setTelefone(String telefone) {
        pedido.setTelefone(telefone);
        return this;
    }

    public PedidoClienteBuilder setEmail(String email) {
        pedido.setEmail(email);
        return this;
    }

    public PedidoClienteBuilder setEnderecoLogradouro(String enderecoLogradouro) {
        pedido.setEnderecoLogradouro(enderecoLogradouro);
        return this;
    }

    public PedidoClienteBuilder setEnderecoNumero(int enderecoNumero) {
        pedido.setEnderecoNumero(enderecoNumero);
        return this;
    }

    public PedidoClienteBuilder setEnderecoComplemento(String enderecoComplemento) {
        pedido.setEnderecoComplemento(enderecoComplemento);
        return this;
    }

    public PedidoClienteBuilder setEnderecoBairro(String enderecoBairro) {
        pedido.setEnderecoBairro(enderecoBairro);
        return this;
    }

    public PedidoClienteBuilder setEnderecoCidade(String enderecoCidade) {
        pedido.setEnderecoCidade(enderecoCidade);
        return this;
    }

    public PedidoClienteBuilder setCep(String cep) {
        pedido.setCep(cep);
        return this;
    }

    public PedidoClienteBuilder setLanchePrincipal(String lanchePrincipal) {
        pedido.setLanchePrincipal(lanchePrincipal);
        return this;
    }

    public PedidoClienteBuilder setAdicionais(String adicionais) {
        pedido.setAdicionais(adicionais);
        return this;
    }

    public PedidoClienteBuilder setBebida(String bebida) {
        pedido.setBebida(bebida);
        return this;
    }

    public PedidoClienteBuilder setSobremesa(String sobremesa) {
        pedido.setSobremesa(sobremesa);
        return this;
    }

    public PedidoClienteBuilder setMetodoPagamento(String metodoPagamento) {
        pedido.setMetodoPagamento(metodoPagamento);
        return this;
    }

    public PedidoClienteBuilder setObservacoes(String observacoes) {
        pedido.setObservacoes(observacoes);
        return this;
    }
}
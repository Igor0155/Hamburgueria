package hamburgueria.comportamentais.mediator;

import hamburgueria.criacionais.builder.PedidoCliente;

public interface DepartamentoServico {
    String receberReclamacao(String mensagem, PedidoCliente pedido);

    String receberElogio(String mensagem, PedidoCliente pedido);

    String receberSugestao(String mensagem, PedidoCliente pedido);
}
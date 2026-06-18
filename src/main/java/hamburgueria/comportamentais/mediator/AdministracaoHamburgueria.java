package hamburgueria.comportamentais.mediator;

import hamburgueria.criacionais.builder.PedidoCliente;

public class AdministracaoHamburgueria implements DepartamentoServico {

    private static AdministracaoHamburgueria instancia = new AdministracaoHamburgueria();

    private AdministracaoHamburgueria() {
    }

    public static AdministracaoHamburgueria getInstancia() {
        return instancia;
    }

    @Override
    public String receberReclamacao(String mensagem, PedidoCliente pedido) {
        return "A Administração vai verificar o problema no pedido #" + pedido.getNumeroPedido() + ": " + mensagem;
    }

    @Override
    public String receberElogio(String mensagem, PedidoCliente pedido) {
        return "A Administração agradece o feedback para o pedido #" + pedido.getNumeroPedido() + ": " + mensagem;
    }

    @Override
    public String receberSugestao(String mensagem, PedidoCliente pedido) {
        return "A Administração vai analisar a sugestão para o pedido #" + pedido.getNumeroPedido() + ": " + mensagem;
    }
}
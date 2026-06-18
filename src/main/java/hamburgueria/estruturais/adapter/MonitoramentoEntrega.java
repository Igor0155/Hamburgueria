package hamburgueria.estruturais.adapter;

import hamburgueria.criacionais.builder.PedidoCliente;

public class MonitoramentoEntrega {

    private PedidoCliente pedido; // INTEGRAÇÃO DIRETA COM O CÉREBRO DO SISTEMA
    private IStatusRastreio rastreioInterno;
    private RastreioAdapter integracaoExterna;

    public MonitoramentoEntrega(PedidoCliente pedido) {
        this.pedido = pedido;
        this.rastreioInterno = new RastreioInterno();
        this.integracaoExterna = new RastreioAdapter(this.rastreioInterno);
    }

    public PedidoCliente getPedido() {
        return this.pedido;
    }

    // A hamburgueria atualiza em String...
    public void atualizarStatusLogistica(String statusTextual) {
        this.rastreioInterno.setStatus(statusTextual);
        this.integracaoExterna.salvarStatus(); // ... e o Adapter salva em Int na API
    }

    public String obterStatusAtual() {
        return this.integracaoExterna.recuperarStatus();
    }

    // Método para provar aos testes que a conversão String -> Int funcionou
    public int obterCodigoExternoDeRastreio() {
        return this.integracaoExterna.getCodigoRastreio();
    }
}
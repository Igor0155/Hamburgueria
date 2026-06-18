package hamburgueria.criacionais.builder;

import hamburgueria.criacionais.abstractfactory.RecipienteBebida;
import hamburgueria.criacionais.abstractfactory.RecipienteLanche;
import hamburgueria.criacionais.abstractfactory.TransportePedido;
import hamburgueria.criacionais.factorymethod.IPagamento;
import hamburgueria.estruturais.composite.ItemCardapio;
import hamburgueria.estruturais.decorator.Lanche;
import hamburgueria.estruturais.facade.PedidoFacade;

public class PedidoCliente {

    private int numeroPedido;
    private String nomeCliente;

    // INTEGRAÇÕES ARQUITETURAIS:
    private IPagamento metodoPagamento; // Factory Method
    private Lanche lanchePrincipal; // Decorator + Prototype
    private ItemCardapio itensExtras; // Composite

    private RecipienteLanche recipienteLanche; // Abstract Factory
    private RecipienteBebida recipienteBebida; // Abstract Factory
    private TransportePedido transportePedido; // Abstract Factory

    public PedidoCliente() {
        this.numeroPedido = 0;
        this.nomeCliente = "";
    }

    // Getters e Setters básicos...
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    // Getters e Setters Integrados...
    public IPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(IPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Lanche getLanchePrincipal() {
        return lanchePrincipal;
    }

    public void setLanchePrincipal(Lanche lanchePrincipal) {
        this.lanchePrincipal = lanchePrincipal;
    }

    public ItemCardapio getItensExtras() {
        return itensExtras;
    }

    public void setItensExtras(ItemCardapio itensExtras) {
        this.itensExtras = itensExtras;
    }

    public RecipienteLanche getRecipienteLanche() {
        return recipienteLanche;
    }

    public void setRecipienteLanche(RecipienteLanche recipienteLanche) {
        this.recipienteLanche = recipienteLanche;
    }

    public RecipienteBebida getRecipienteBebida() {
        return recipienteBebida;
    }

    public void setRecipienteBebida(RecipienteBebida recipienteBebida) {
        this.recipienteBebida = recipienteBebida;
    }

    public TransportePedido getTransportePedido() {
        return transportePedido;
    }

    public void setTransportePedido(TransportePedido transportePedido) {
        this.transportePedido = transportePedido;
    }

    // INTEGRAÇÃO COM A FACADE
    public boolean despacharPedido() {
        return PedidoFacade.verificarPendenciasLiberacao(this);
    }
}
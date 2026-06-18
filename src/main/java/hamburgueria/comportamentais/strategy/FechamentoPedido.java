package hamburgueria.comportamentais.strategy;

import hamburgueria.criacionais.builder.PedidoCliente;

public class FechamentoPedido {

    private float valorProcessado;

    public float getValorProcessado() {
        return valorProcessado;
    }

    public void calcularPrecoNormal(PedidoCliente pedido) {
        CalculadoraTotal calculadora = new CalculadoraTotal(pedido);
        this.valorProcessado = calculadora.calcular(new PrecoNormal());
    }

    public void calcularDescontoAniversario(PedidoCliente pedido) {
        CalculadoraTotal calculadora = new CalculadoraTotal(pedido);
        this.valorProcessado = calculadora.calcular(new DescontoAniversario());
    }

    public void calcularDescontoPrimeiraCompra(PedidoCliente pedido) {
        CalculadoraTotal calculadora = new CalculadoraTotal(pedido);
        this.valorProcessado = calculadora.calcular(new DescontoPrimeiraCompra());
    }

    public void calcularTaxaMadrugada(PedidoCliente pedido) {
        CalculadoraTotal calculadora = new CalculadoraTotal(pedido);
        this.valorProcessado = calculadora.calcular(new TaxaMadrugada());
    }

    public void calcularAcrescimoFeriado(PedidoCliente pedido) {
        CalculadoraTotal calculadora = new CalculadoraTotal(pedido);
        this.valorProcessado = calculadora.calcular(new AcrescimoFeriado());
    }
}
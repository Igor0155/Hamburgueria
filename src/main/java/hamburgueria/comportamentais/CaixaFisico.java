package hamburgueria.comportamentais;

public class CaixaFisico extends ProcessamentoCaixa {
    protected String contarValores() {
        return "Contando notas, moedas e cupons de cartão na gaveta";
    }

    protected String emitirComprovante() {
        return "Imprimindo fita na impressora fiscal";
    }
}
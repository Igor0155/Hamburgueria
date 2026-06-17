package hamburgueria.comportamentais;

public class CaixaFisico extends ProcessamentoCaixa {

    @Override
    protected String contarValores() {
        return "Contando notas, moedas e cupons de cartão na gaveta";
    }

    @Override
    protected String emitirComprovante() {
        return "Imprimindo fita na impressora fiscal";
    }
}
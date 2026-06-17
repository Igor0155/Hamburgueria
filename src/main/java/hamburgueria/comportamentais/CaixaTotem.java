package hamburgueria.comportamentais;

public class CaixaTotem extends ProcessamentoCaixa {

    @Override
    protected String contarValores() {
        return "Fechamento automático: apenas transações eletrônicas (Cartão/Pix)";
    }

    @Override
    protected String emitirComprovante() {
        return "Enviando comprovante z-reading por e-mail para o gerente";
    }
}
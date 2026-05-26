package hamburgueria.comportamentais;

public class CaixaTotem extends ProcessamentoCaixa {
    protected String contarValores() {
        return "Fechamento automático: apenas transações eletrônicas (Cartão/Pix)";
    }

    protected String emitirComprovante() {
        return "Enviando comprovante z-reading por e-mail para o gerente";
    }
}
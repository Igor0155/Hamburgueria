package hamburgueria.criacionais.factorymethod;

public class PagamentoFiado {

    public String processar() {
        return "Pagamento anotado no caderno";
    }

    public String cancelar() {
        return "Dívida perdoada";
    }
}
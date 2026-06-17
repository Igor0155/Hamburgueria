package hamburgueria.criacionais;

public class CaixaTermicaDelivery implements CaixaLanche {
    @Override
    public String empacotar() {
        return "Lanche na Caixa Térmica Lacrada";
    }
}
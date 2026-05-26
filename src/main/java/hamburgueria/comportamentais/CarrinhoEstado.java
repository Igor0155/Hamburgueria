package hamburgueria.comportamentais;

public class CarrinhoEstado {
    private String conteudoSalvo;

    public CarrinhoEstado(String conteudo) {
        this.conteudoSalvo = conteudo;
    }

    public String getConteudoSalvo() {
        return conteudoSalvo;
    }
}
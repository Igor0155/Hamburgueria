package hamburgueria.comportamentais;

public class CarrinhoCompras {
    private String conteudoAtual = "";

    public void adicionarItem(String item) {
        conteudoAtual += (conteudoAtual.isEmpty() ? "" : ", ") + item;
    }

    public String getConteudoAtual() {
        return conteudoAtual;
    }

    // Salva o estado atual
    public CarrinhoEstado salvar() {
        return new CarrinhoEstado(conteudoAtual);
    }

    // Restaura um estado do passado
    public void restaurar(CarrinhoEstado estadoPassado) {
        this.conteudoAtual = estadoPassado.getConteudoSalvo();
    }
}
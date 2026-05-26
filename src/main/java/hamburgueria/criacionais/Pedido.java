package hamburgueria.criacionais;

import hamburgueria.ComponenteCardapio;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String nomeCliente;
    private Pagamento pagamento;
    private EmbalagemFactory embalagem;
    private List<ComponenteCardapio> itens = new ArrayList<>();

    // Setters
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setEmbalagem(EmbalagemFactory embalagem) {
        this.embalagem = embalagem;
    }

    public void adicionarItem(ComponenteCardapio item) {
        this.itens.add(item);
    }

    // Getters
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public Pagamento getPagamento() {
        return this.pagamento;
    }

    public EmbalagemFactory getEmbalagem() {
        return this.embalagem;
    }

    public int getQuantidadeItens() {
        return this.itens.size();
    }
}
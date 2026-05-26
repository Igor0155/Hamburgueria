package hamburgueria;

import java.util.ArrayList;
import java.util.List;

public class Categoria implements ComponenteCardapio {
    private String nome;
    private List<ComponenteCardapio> itens = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteCardapio item) {
        this.itens.add(item);
    }

    public String getDescricao() {
        StringBuilder descricao = new StringBuilder(this.nome + " contendo: [");
        for (int i = 0; i < itens.size(); i++) {
            descricao.append(itens.get(i).getDescricao());
            if (i < itens.size() - 1) {
                descricao.append(", ");
            }
        }
        descricao.append("]");
        return descricao.toString();
    }

    public float getPreco() {
        float total = 0;
        for (ComponenteCardapio item : itens) {
            total += item.getPreco();
        }
        return total; // O preço da categoria/combo é a soma de tudo que tem dentro
    }
}
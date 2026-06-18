package hamburgueria.estruturais.composite;

import java.util.ArrayList;
import java.util.List;

public class CategoriaCardapio extends ItemCardapio {

    private List<ItemCardapio> conteudos;

    public CategoriaCardapio(String descricao) {
        super(descricao);
        this.conteudos = new ArrayList<ItemCardapio>();
    }

    public void addItem(ItemCardapio conteudo) {
        this.conteudos.add(conteudo);
    }

    public List<ItemCardapio> getConteudos() {
        return conteudos;
    }

    public String getDetalhes() {
        String saida = "";
        saida = "Categoria: " + this.getDescricao() + "\n";
        for (ItemCardapio conteudo : conteudos) {
            saida += conteudo.getDetalhes();
        }
        return saida;
    }
}
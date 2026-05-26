package hamburgueria.estruturais;

public class ItemLanche {
    private int quantidadeGramas; // Estado Extrínseco
    private TipoIngrediente tipo; // Estado Intrínseco

    public ItemLanche(int quantidadeGramas, TipoIngrediente tipo) {
        this.quantidadeGramas = quantidadeGramas;
        this.tipo = tipo;
    }

    public String getResumo() {
        return quantidadeGramas + "g de " + tipo.getNome();
    }
}
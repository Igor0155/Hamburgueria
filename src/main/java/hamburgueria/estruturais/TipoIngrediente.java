package hamburgueria.estruturais;

public class TipoIngrediente {
    private String nome;
    private String fornecedor;
    private String dadosNutricionais;

    public TipoIngrediente(String nome, String fornecedor, String dadosNutricionais) {
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.dadosNutricionais = dadosNutricionais;
    }

    public String getNome() {
        return nome;
    }
}
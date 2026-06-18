package hamburgueria.estruturais.decorator;

// O Prototype nasce na própria interface do sistema de lanches!
public interface Lanche extends Cloneable {
    float getPreco();

    String getDescricao();

    Lanche clone() throws CloneNotSupportedException;
}
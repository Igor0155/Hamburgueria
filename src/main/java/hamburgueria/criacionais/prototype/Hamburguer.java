package hamburgueria.criacionais.prototype;

public class Hamburguer implements Cloneable {
    private int id;
    private String nome;
    private Fornecedor fornecedor;
    private String tipoCarne;

    public Hamburguer(int id, String nome, Fornecedor fornecedor, String tipoCarne) {
        this.id = id;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.tipoCarne = tipoCarne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTipoCarne() {
        return tipoCarne;
    }

    public void setTipoCarne(String tipoCarne) {
        this.tipoCarne = tipoCarne;
    }

    @Override
    public Hamburguer clone() throws CloneNotSupportedException {
        Hamburguer hamburguerClone = (Hamburguer) super.clone();
        hamburguerClone.fornecedor = (Fornecedor) hamburguerClone.fornecedor.clone();
        return hamburguerClone;
    }

    @Override
    public String toString() {
        return "Hamburguer{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fornecedor=" + fornecedor +
                ", tipoCarne='" + tipoCarne + '\'' +
                '}';
    }
}
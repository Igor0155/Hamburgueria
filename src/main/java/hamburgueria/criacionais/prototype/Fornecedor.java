package hamburgueria.criacionais.prototype;

public class Fornecedor implements Cloneable {
    private String nomeEmpresa;
    private Integer codigo;

    public Fornecedor(String nomeEmpresa, Integer codigo) {
        super();
        this.nomeEmpresa = nomeEmpresa;
        this.codigo = codigo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nomeEmpresa='" + nomeEmpresa + '\'' +
                ", codigo=" + codigo +
                '}';
    }
}
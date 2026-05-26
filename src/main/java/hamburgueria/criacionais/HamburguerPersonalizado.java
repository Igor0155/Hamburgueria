package hamburgueria.criacionais;

public class HamburguerPersonalizado implements Cloneable {
    private String pao;
    private String carne;
    private String queijo;
    private String molhoEspecial;

    public HamburguerPersonalizado(String pao, String carne, String queijo, String molhoEspecial) {
        this.pao = pao;
        this.carne = carne;
        this.queijo = queijo;
        this.molhoEspecial = molhoEspecial;
    }

    public void setPao(String pao) {
        this.pao = pao;
    }

    public String getPao() {
        return pao;
    }

    public String getCarne() {
        return carne;
    }

    public String getQueijo() {
        return queijo;
    }

    public String getMolhoEspecial() {
        return molhoEspecial;
    }

    @Override
    public HamburguerPersonalizado clone() {
        try {
            return (HamburguerPersonalizado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar o hambúrguer", e);
        }
    }

    public String getDescricao() {
        return "Hamburguer com Pão " + pao + ", Carne de " + carne + ", Queijo " + queijo + " e Molho " + molhoEspecial;
    }
}
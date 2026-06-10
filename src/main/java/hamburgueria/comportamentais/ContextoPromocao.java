package hamburgueria.comportamentais;

public class ContextoPromocao {
    private String diaDaSemana;
    private boolean estudante;
    private boolean clienteVip;

    public ContextoPromocao(String diaDaSemana, boolean estudante, boolean clienteVip) {
        this.diaDaSemana = diaDaSemana;
        this.estudante = estudante;
        this.clienteVip = clienteVip;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public boolean isEstudante() {
        return estudante;
    }

    public boolean isClienteVip() {
        return clienteVip;
    }
}
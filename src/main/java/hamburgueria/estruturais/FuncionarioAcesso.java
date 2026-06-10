package hamburgueria.estruturais;

public class FuncionarioAcesso {
    private String nome;
    private String cargo;

    public FuncionarioAcesso(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }
}
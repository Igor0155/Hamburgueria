package hamburgueria.estruturais.proxy;

import hamburgueria.comportamentais.visitor.IElementoAuditoria;
import hamburgueria.comportamentais.visitor.VisitorAuditoria;

public class Funcionario implements IElementoAuditoria {
    private String nome;
    private boolean gerente;

    public Funcionario(String nome, boolean gerente) {
        this.nome = nome;
        this.gerente = gerente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isGerente() {
        return gerente;
    }

    public void setGerente(boolean gerente) {
        this.gerente = gerente;
    }

    @Override
    public String aceitar(VisitorAuditoria visitor) {
        return visitor.exibirFuncionario(this);
    }
}
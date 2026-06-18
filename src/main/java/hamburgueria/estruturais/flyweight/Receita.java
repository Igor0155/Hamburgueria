package hamburgueria.estruturais.flyweight;

import hamburgueria.comportamentais.visitor.IElementoAuditoria;
import hamburgueria.comportamentais.visitor.VisitorAuditoria;

public class Receita implements IElementoAuditoria {

    private String nomeLanche;
    private String modoDePreparo;
    private int tempoEstimadoMinutos;

    public Receita(String nomeLanche, String modoDePreparo, int tempoEstimadoMinutos) {
        this.nomeLanche = nomeLanche;
        this.modoDePreparo = modoDePreparo;
        this.tempoEstimadoMinutos = tempoEstimadoMinutos;
    }

    public String getNomeLanche() {
        return nomeLanche;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public int getTempoEstimadoMinutos() {
        return tempoEstimadoMinutos;
    }

    @Override
    public String aceitar(VisitorAuditoria visitor) {
        return visitor.exibirReceita(this);
    }
}
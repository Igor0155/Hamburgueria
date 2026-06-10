package hamburgueria.comportamentais;

public class ExpressaoDiaSemana implements ExpressaoPromocao {
    private String diaRequisitado;

    public ExpressaoDiaSemana(String diaRequisitado) {
        this.diaRequisitado = diaRequisitado;
    }

    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return contexto.getDiaDaSemana().equalsIgnoreCase(this.diaRequisitado);
    }
}
package hamburgueria.comportamentais;

public class ExpressaoEstudante implements ExpressaoPromocao {
    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return contexto.isEstudante();
    }
}
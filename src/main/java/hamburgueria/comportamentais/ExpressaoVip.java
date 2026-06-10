package hamburgueria.comportamentais;

public class ExpressaoVip implements ExpressaoPromocao {
    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return contexto.isClienteVip();
    }
}
package hamburgueria.comportamentais;

public class ExpressaoAnd implements ExpressaoPromocao {
    private ExpressaoPromocao expressaoEsquerda;
    private ExpressaoPromocao expressaoDireita;

    public ExpressaoAnd(ExpressaoPromocao esquerda, ExpressaoPromocao direita) {
        this.expressaoEsquerda = esquerda;
        this.expressaoDireita = direita;
    }

    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return expressaoEsquerda.interpretar(contexto) && expressaoDireita.interpretar(contexto);
    }
}
package hamburgueria.comportamentais;

public class Cozinha {

    public String iniciarPreparo(String lanche) {
        return "Cozinha: Separando ingredientes e iniciando o preparo do " + lanche;
    }

    public String abortarPreparo(String lanche) {
        return "Cozinha: Abortando o preparo do " + lanche + " e descartando itens na chapa";
    }
}
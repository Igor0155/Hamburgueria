package hamburgueria.comportamentais.interpreter;

import hamburgueria.criacionais.builder.PedidoCliente;

public class MotorCalculoDinamico {

    public static double calcularValorComRegraDinamica(PedidoCliente pedido, String formulaDinamica, double taxaFrete) {
        // Extrai o valor real do objeto integrado
        double precoLanche = pedido.getLanchePrincipal().getPreco();

        // Substitui as variáveis da String pelos valores reais do sistema
        String expressao = formulaDinamica.replace("lanchePreco", Double.toString(precoLanche));
        expressao = expressao.replace("taxaFrete", Double.toString(taxaFrete));

        // O Interpreter resolve a matemática da String
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas(expressao);
        return interpretador.interpretar();
    }
}
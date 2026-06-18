package hamburgueria.comportamentais.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class InterpretadorExpressaoTest {

    @Test
    void deveCalcularExpressaoSoma() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("6 + 2");
        assertEquals(8.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoSubtracao() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("6 - 2");
        assertEquals(4.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoMultiplicacao() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("6 * 2");
        assertEquals(12.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoDivisao() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("6 / 2");
        assertEquals(3.0, interpretador.interpretar());
    }

    @Test
    void deveCalcularExpressaoCombinada() {
        InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("10 / 2 * 3 + 1 - 4");
        assertEquals(12.0, interpretador.interpretar());
    }

    @Test
    void deveRetornarExcecaoElementoInvalido() {
        try {
            InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("2 ^ 2");
            interpretador.interpretar();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expressão com elemento inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoExpressaoInvalida() {
        try {
            InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("2 +");
            interpretador.interpretar();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expressão inválida", e.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoParaDivisaoPorZero() {
        try {
            InterpretadorExpressao interpretador = new InterpretadorExpressoesAritmeticas("10 / 0");
            interpretador.interpretar();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Divisão por zero", e.getMessage());
        }
    }
}
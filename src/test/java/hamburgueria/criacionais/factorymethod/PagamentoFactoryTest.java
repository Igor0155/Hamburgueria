package hamburgueria.criacionais.factorymethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class PagamentoFactoryTest {

    @Test
    void deveProcessarPagamentoPix() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("Pix");
        assertEquals("Pagamento via PIX efetivado", pagamento.processar());
    }

    @Test
    void deveCancelarPagamentoPix() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("Pix");
        assertEquals("Pagamento via PIX cancelado", pagamento.cancelar());
    }

    @Test
    void deveProcessarPagamentoCartaoCredito() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("CartaoCredito");
        assertEquals("Pagamento via Cartão de Crédito efetivado", pagamento.processar());
    }

    @Test
    void deveCancelarPagamentoCartaoCredito() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("CartaoCredito");
        assertEquals("Estorno no Cartão de Crédito efetivado", pagamento.cancelar());
    }

    @Test
    void deveProcessarPagamentoDinheiro() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("Dinheiro");
        assertEquals("Pagamento em Dinheiro recebido no caixa", pagamento.processar());
    }

    @Test
    void deveCancelarPagamentoDinheiro() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("Dinheiro");
        assertEquals("Dinheiro devolvido ao cliente", pagamento.cancelar());
    }

    @Test
    void deveProcessarPagamentoValeRefeicao() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("ValeRefeicao");
        assertEquals("Pagamento via Vale Refeição efetivado", pagamento.processar());
    }

    @Test
    void deveCancelarPagamentoValeRefeicao() {
        IPagamento pagamento = PagamentoFactory.obterPagamento("ValeRefeicao");
        assertEquals("Pagamento via Vale Refeição cancelado", pagamento.cancelar());
    }

    @Test
    void deveRetornarExcecaoParaPagamentoInexistente() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            PagamentoFactory.obterPagamento("Criptomoeda");
        });
        assertEquals("Pagamento inexistente", excecao.getMessage());
    }

    @Test
    void deveRetornarExcecaoParaPagamentoInvalido() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            PagamentoFactory.obterPagamento("Fiado"); // Existe a classe, mas NÃO implementa IPagamento
        });
        assertEquals("Pagamento inválido", excecao.getMessage());
    }
}
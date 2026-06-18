package hamburgueria.criacionais.abstractfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FabricaAbstrataTest {

    @Test
    void deveEmbalarLancheDelivery() {
        FabricaAbstrata fabrica = new FabricaDelivery();
        assertEquals("Lanche embalado na Caixa Térmica", fabrica.createRecipienteLanche().embalar());
    }

    @Test
    void deveServirBebidaDelivery() {
        FabricaAbstrata fabrica = new FabricaDelivery();
        assertEquals("Bebida no Copo Selado Antivazamento", fabrica.createRecipienteBebida().servir());
    }

    @Test
    void deveDespacharPedidoDelivery() {
        FabricaAbstrata fabrica = new FabricaDelivery();
        assertEquals("Pedido despachado em Sacola com Lacre de Segurança",
                fabrica.createTransportePedido().despachar());
    }

    @Test
    void deveEmbalarLancheSalao() {
        FabricaAbstrata fabrica = new FabricaSalao();
        assertEquals("Lanche servido na Cesta de Madeira", fabrica.createRecipienteLanche().embalar());
    }

    @Test
    void deveServirBebidaSalao() {
        FabricaAbstrata fabrica = new FabricaSalao();
        assertEquals("Bebida servida no Copo de Vidro", fabrica.createRecipienteBebida().servir());
    }

    @Test
    void deveDespacharPedidoSalao() {
        FabricaAbstrata fabrica = new FabricaSalao();
        assertEquals("Pedido entregue na Bandeja de Mesa", fabrica.createTransportePedido().despachar());
    }

    @Test
    void deveEmbalarLancheDriveThru() {
        FabricaAbstrata fabrica = new FabricaDriveThru();
        assertEquals("Lanche embalado na Caixa de Papelão", fabrica.createRecipienteLanche().embalar());
    }

    @Test
    void deveServirBebidaDriveThru() {
        FabricaAbstrata fabrica = new FabricaDriveThru();
        assertEquals("Bebida no Copo de Plástico com Tampa", fabrica.createRecipienteBebida().servir());
    }

    @Test
    void deveDespacharPedidoDriveThru() {
        FabricaAbstrata fabrica = new FabricaDriveThru();
        assertEquals("Pedido entregue na Sacola de Papel Kraft", fabrica.createTransportePedido().despachar());
    }
}
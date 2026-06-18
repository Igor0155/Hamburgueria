package hamburgueria.criacionais.abstractfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PedidoTest {

    // Testes da Fábrica de Delivery
    @Test
    void deveEmbalarLancheDelivery() {
        FabricaAbstrata fabrica = new FabricaDelivery();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Lanche embalado na Caixa Térmica", pedido.embalarLanche());
    }

    @Test
    void deveServirBebidaDelivery() {
        FabricaAbstrata fabrica = new FabricaDelivery();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Bebida no Copo Selado Antivazamento", pedido.servirBebida());
    }

    @Test
    void deveDespacharPedidoDelivery() {
        FabricaAbstrata fabrica = new FabricaDelivery();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Pedido despachado em Sacola com Lacre de Segurança", pedido.despacharPedido());
    }

    // Testes da Fábrica de Salão
    @Test
    void deveEmbalarLancheSalao() {
        FabricaAbstrata fabrica = new FabricaSalao();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Lanche servido na Cesta de Madeira", pedido.embalarLanche());
    }

    @Test
    void deveServirBebidaSalao() {
        FabricaAbstrata fabrica = new FabricaSalao();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Bebida servida no Copo de Vidro", pedido.servirBebida());
    }

    @Test
    void deveDespacharPedidoSalao() {
        FabricaAbstrata fabrica = new FabricaSalao();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Pedido entregue na Bandeja de Mesa", pedido.despacharPedido());
    }

    // Testes da Fábrica de Drive-Thru
    @Test
    void deveEmbalarLancheDriveThru() {
        FabricaAbstrata fabrica = new FabricaDriveThru();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Lanche embalado na Caixa de Papelão", pedido.embalarLanche());
    }

    @Test
    void deveServirBebidaDriveThru() {
        FabricaAbstrata fabrica = new FabricaDriveThru();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Bebida no Copo de Plástico com Tampa", pedido.servirBebida());
    }

    @Test
    void deveDespacharPedidoDriveThru() {
        FabricaAbstrata fabrica = new FabricaDriveThru();
        Pedido pedido = new Pedido(fabrica);
        assertEquals("Pedido entregue na Sacola de Papel Kraft", pedido.despacharPedido());
    }
}
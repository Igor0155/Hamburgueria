package hamburgueria.comportamentais;

import java.util.ArrayList;
import java.util.List;

public class FilaPedidosCozinha {
    private List<String> pedidos = new ArrayList<>();

    public void adicionarPedido(String pedido) {
        this.pedidos.add(pedido);
    }

    public PedidoIterator criarIterador() {
        return new FilaIterator(this.pedidos);
    }

    private class FilaIterator implements PedidoIterator {
        private List<String> lista;
        private int posicaoAtual = 0;

        public FilaIterator(List<String> lista) {
            this.lista = lista;
        }

        @Override
        public boolean hasNext() {
            return posicaoAtual < lista.size();
        }

        @Override
        public String next() {
            if (this.hasNext()) {
                String pedido = lista.get(posicaoAtual);
                posicaoAtual++;
                return pedido;
            }
            return null;
        }
    }
}
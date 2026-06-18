package hamburgueria.estruturais.adapter;

public class RastreioAdapter extends SistemaLogisticaExterno {

    private IStatusRastreio rastreioInterno;

    public RastreioAdapter(IStatusRastreio rastreioInterno) {
        this.rastreioInterno = rastreioInterno;
    }

    public String recuperarStatus() {
        if (this.getCodigoRastreio() == 1)
            rastreioInterno.setStatus("CRIADO");
        else if (this.getCodigoRastreio() == 2)
            rastreioInterno.setStatus("PREPARANDO");
        else if (this.getCodigoRastreio() == 3)
            rastreioInterno.setStatus("SAIU_ENTREGA");
        else if (this.getCodigoRastreio() == 4)
            rastreioInterno.setStatus("CONCLUIDO");
        else
            rastreioInterno.setStatus("DESCONHECIDO");

        return rastreioInterno.getStatus();
    }

    public void salvarStatus() {
        if (rastreioInterno.getStatus().equals("CRIADO"))
            this.setCodigoRastreio(1);
        else if (rastreioInterno.getStatus().equals("PREPARANDO"))
            this.setCodigoRastreio(2);
        else if (rastreioInterno.getStatus().equals("SAIU_ENTREGA"))
            this.setCodigoRastreio(3);
        else if (rastreioInterno.getStatus().equals("CONCLUIDO"))
            this.setCodigoRastreio(4);
        else
            this.setCodigoRastreio(0); // Erro / Não Mapeado
    }
}
package hamburgueria.estruturais.adapter;

public class RastreioInterno implements IStatusRastreio {

    private String status;

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
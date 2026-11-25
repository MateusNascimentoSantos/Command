package restaurante;

public class OcupacaoMesaTarefa implements Tarefa {

    private Mesa mesa;
    private MesaMemento estadoAnterior;

    public OcupacaoMesaTarefa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void executar() {
        this.estadoAnterior = this.mesa.salvarEstado();
        this.mesa.ocuparMesa();
    }

    public void cancelar() {
        this.mesa.restaurarEstado(this.estadoAnterior);
    }
}

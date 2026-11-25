package restaurante;

public class ReservaMesaTarefa implements Tarefa {

    private Mesa mesa;
    private MesaMemento estadoAnterior;

    public ReservaMesaTarefa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void executar() {
        this.estadoAnterior = this.mesa.salvarEstado();
        this.mesa.reservarMesa();
    }

    public void cancelar() {
        this.mesa.restaurarEstado(this.estadoAnterior);
    }
}

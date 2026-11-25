package restaurante;

public class LiberacaoMesaTarefa implements Tarefa {

    private Mesa mesa;
    private MesaMemento estadoAnterior;

    public LiberacaoMesaTarefa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void executar() {
        this.estadoAnterior = this.mesa.salvarEstado();
        this.mesa.liberarMesa();
    }

    public void cancelar() {
        this.mesa.restaurarEstado(this.estadoAnterior);
    }
}

package restaurante;

public class Mesa {

    private int numero;
    private int lugares;
    private String situacao;

    public Mesa(int numero, int lugares) {
        this.numero = numero;
        this.lugares = lugares;
        this.situacao = "Mesa livre";
    }

    public String getSituacao() {
        return situacao;
    }

    public MesaMemento salvarEstado() {
        return new MesaMemento(this.situacao);
    }

    public void restaurarEstado(MesaMemento memento) {
        this.situacao = memento.getSituacao();
    }

    public void ocuparMesa() {
        this.situacao = "Mesa ocupada";
    }

    public void liberarMesa() {
        this.situacao = "Mesa livre";
    }

    public void reservarMesa() {
        this.situacao = "Mesa reservada";
    }

    public void unirMesa() {
        this.situacao = "Mesa unida";
    }
}

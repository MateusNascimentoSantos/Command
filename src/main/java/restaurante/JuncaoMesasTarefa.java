package restaurante;

public class JuncaoMesasTarefa implements Tarefa {

    private Mesa principal;
    private Mesa secundaria;
    private MesaMemento estadoPrincipal;
    private MesaMemento estadoSecundaria;

    public JuncaoMesasTarefa(Mesa principal, Mesa secundaria) {
        this.principal = principal;
        this.secundaria = secundaria;
    }

    public void executar() {
        this.estadoPrincipal = this.principal.salvarEstado();
        this.estadoSecundaria = this.secundaria.salvarEstado();
        this.principal.ocuparMesa();
        this.secundaria.unirMesa();
    }

    public void cancelar() {
        this.principal.restaurarEstado(this.estadoPrincipal);
        this.secundaria.restaurarEstado(this.estadoSecundaria);
    }
}

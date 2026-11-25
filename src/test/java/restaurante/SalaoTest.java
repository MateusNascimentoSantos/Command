package restaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaoTest {

    Salao salao;
    Mesa mesa;
    Mesa vizinha;

    @BeforeEach
    void setUp() {
        salao = new Salao();
        mesa = new Mesa(12, 4);
        vizinha = new Mesa(13, 4);
    }

    @Test
    void deveOcuparMesa() {
        salao.executarTarefa(new OcupacaoMesaTarefa(mesa));

        assertEquals("Mesa ocupada", mesa.getSituacao());
    }

    @Test
    void deveLiberarMesa() {
        salao.executarTarefa(new OcupacaoMesaTarefa(mesa));
        salao.executarTarefa(new LiberacaoMesaTarefa(mesa));

        assertEquals("Mesa livre", mesa.getSituacao());
    }

    @Test
    void deveReservarMesa() {
        salao.executarTarefa(new ReservaMesaTarefa(mesa));

        assertEquals("Mesa reservada", mesa.getSituacao());
    }

    @Test
    void deveJuntarDuasMesas() {
        salao.executarTarefa(new JuncaoMesasTarefa(mesa, vizinha));

        assertEquals("Mesa ocupada", mesa.getSituacao());
        assertEquals("Mesa unida", vizinha.getSituacao());
    }

    @Test
    void deveCancelarLiberacaoMesa() {
        salao.executarTarefa(new OcupacaoMesaTarefa(mesa));
        salao.executarTarefa(new LiberacaoMesaTarefa(mesa));

        salao.cancelarUltimaTarefa();

        assertEquals("Mesa ocupada", mesa.getSituacao());
    }

    @Test
    void deveCancelarDuasTarefasEmSequencia() {
        salao.executarTarefa(new OcupacaoMesaTarefa(mesa));
        salao.executarTarefa(new LiberacaoMesaTarefa(mesa));

        salao.cancelarUltimaTarefa();
        salao.cancelarUltimaTarefa();

        assertEquals("Mesa livre", mesa.getSituacao());
    }

    @Test
    void deveCancelarApenasAMesaDaUltimaTarefa() {
        salao.executarTarefa(new OcupacaoMesaTarefa(mesa));
        salao.executarTarefa(new OcupacaoMesaTarefa(vizinha));

        salao.cancelarUltimaTarefa();

        assertEquals("Mesa ocupada", mesa.getSituacao());
        assertEquals("Mesa livre", vizinha.getSituacao());
    }

    @Test
    void deveDevolverAReservaAoCancelarAJuncao() {
        salao.executarTarefa(new ReservaMesaTarefa(mesa));
        salao.executarTarefa(new JuncaoMesasTarefa(mesa, vizinha));

        salao.cancelarUltimaTarefa();

        assertEquals("Mesa reservada", mesa.getSituacao());
        assertEquals("Mesa livre", vizinha.getSituacao());
    }

    @Test
    void deveRestaurarOEstadoAnteriorENaoOInversoFixo() {
        salao.executarTarefa(new ReservaMesaTarefa(mesa));
        salao.executarTarefa(new OcupacaoMesaTarefa(mesa));

        salao.cancelarUltimaTarefa();

        assertEquals("Mesa reservada", mesa.getSituacao());
    }

    @Test
    void naoDeveFazerNadaAoCancelarSemTarefaExecutada() {
        salao.cancelarUltimaTarefa();

        assertEquals("Mesa livre", mesa.getSituacao());
    }
}

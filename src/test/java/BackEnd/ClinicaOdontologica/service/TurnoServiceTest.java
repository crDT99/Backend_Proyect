package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Domicilio;
import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.entity.Turno;
import BackEnd.ClinicaOdontologica.utils.exeptions.IdNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    public void guardarTurno(){
        Paciente paciente= new Paciente("Bart","Simpson","65543321", "bart@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        Odontologo odontologo = new Odontologo("OD11", "Neymar", "Junior");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        Turno turno = new Turno(LocalDate.of(2024,10,18), pacienteGuardado, odontologoGuardado);
        Turno turnoGuardado = turnoService.guardarTurno(turno);
        assertEquals(3L,turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Long id = 3L;
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno(){
        Long id = 3L;
        Optional<Paciente> paciente = pacienteService.buscarPorID(id);
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        Turno turno = new Turno(id, LocalDate.of(2024,8,15), paciente.get(), odontologo.get());
        turnoService.actualizarTurno(turno);
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        assertEquals(LocalDate.of(2024,8,15), turnoBuscado.get().getFecha());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Turno> listarTurnos = turnoService.buscarTodos();
        System.out.println(listarTurnos);
        assertEquals(3, listarTurnos.size());
    }

    @Test
    @Order(5)
    public void eliminarTurno(){
        turnoService.eliminarTurnoPorId(3L);
        assertThrows(IdNotFoundException.class,()->{
            Optional<Turno> turnoEliminado = turnoService.buscarPorId(3L);
        });
    }
}

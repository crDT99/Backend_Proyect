package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Domicilio;
import BackEnd.ClinicaOdontologica.entity.Paciente;
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
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente= new Paciente("Bart","Simpson","65543321", "bart@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA"));
        Paciente pacienteGuardado= pacienteService.guardarPaciente(paciente);
        assertEquals(3L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Long id= 3L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPaciente(){
        Long id= 3L;
        Paciente paciente= new Paciente(id,"Lisa","Simpson","65543321", "lisa@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA"));
        pacienteService.actualizarPaciente(paciente);
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);
        assertEquals("Lisa", pacienteBuscado.get().getNombre());
            }

   @Test
   @Order(4)
   public void ListarTodos(){
        List<Paciente> listaPacientes= pacienteService.buscarTodosPacientes();
        assertEquals(3,listaPacientes.size());
   }
   @Test
   @Order(5)
   public void eliminarPaciente(){
        pacienteService.eliminarPacientePorId(3L);
        assertThrows(IdNotFoundException.class,()->{
            Optional<Paciente> pacienteEliminado= pacienteService.buscarPorID(3L);
        });
   }
}

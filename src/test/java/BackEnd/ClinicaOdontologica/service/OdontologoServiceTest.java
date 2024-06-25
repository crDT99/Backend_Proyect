package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.utils.exeptions.IdNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("OD11", "Naymar", "Junior");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(3L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorId(){
        Long id = 3L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id = 3L;
        Odontologo odontologo = new Odontologo(id, "OD11", "Vinicius", "Junior");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertEquals("Vinicius", odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Odontologo> listaOdontologos = odontologoService.buscarTodosOdontologos();
        assertEquals(3, listaOdontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologoPorId(3L);
        assertThrows(IdNotFoundException.class,()->{
            Optional<Odontologo> odontologoEliminado = odontologoService.buscarPorId(3L);
        });
    }
}

package BackEnd.ClinicaOdontologica;

import BackEnd.ClinicaOdontologica.entity.Turno;
import BackEnd.ClinicaOdontologica.entity.Domicilio;
import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.service.OdontologoService;
import BackEnd.ClinicaOdontologica.service.PacienteService;
import BackEnd.ClinicaOdontologica.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnosIntegracionTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private void cargarDatos(){
        Paciente paciente= pacienteService.guardarPaciente(new Paciente("Bart","Simpson","65543321", "bart@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
        Odontologo odontologo= odontologoService.guardarOdontologo(new Odontologo("OD11", "Neymar", "Junior"));
        Turno turno= turnoService.guardarTurno(new Turno(LocalDate.of(2024,10,18),paciente,odontologo));
    }
    @Test
    public void ListarTodosLosTurnosTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos/listartodos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void BuscarTurnoPorIdIsOkTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos/{id}", 3L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void BuscarTurnoPorIdIsNotFoundTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos/{id}", 300L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void GuardarTurnoTest() throws Exception{
        Paciente paciente1= pacienteService.guardarPaciente(new Paciente("Lisa","Simpson","65543321", "lisa@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
        Odontologo odontologo1= odontologoService.guardarOdontologo(new Odontologo("OD12", "Vinicius", "Junior"));
        Turno turno1= turnoService.guardarTurno(new Turno(LocalDate.of(2024,10,18),paciente1,odontologo1));

        String turno1Json = objectMapper.writeValueAsString(turno1);

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(turno1Json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(turno1Json, respuesta.getResponse().getContentAsString());
    }
    @Test
    public void actualizarTurnoTest() throws Exception{
        Paciente paciente2= pacienteService.guardarPaciente(new Paciente("Marge","Simpson","65543321", "marge@boubier.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
        Odontologo odontologo2= odontologoService.guardarOdontologo(new Odontologo("OD13", "Zlatan", "Ibraimovic"));
        Turno turno2= turnoService.guardarTurno(new Turno(LocalDate.of(2024,9,14),paciente2,odontologo2));

        String turno2Json = objectMapper.writeValueAsString(turno2);

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(turno2Json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals("Turno actualizado con exito", respuesta.getResponse().getContentAsString());
    }
    @Test
    public void EliminarTurnoIsOkTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/{id}", 3L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void EliminarTurnoIsNotFoundTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/{id}", 100L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals("No se encontro el recurso con la Id: 100 en el modelo de Turno", respuesta.getResponse().getContentAsString());
    }
}
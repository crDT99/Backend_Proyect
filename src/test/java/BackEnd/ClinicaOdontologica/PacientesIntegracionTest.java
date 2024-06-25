package BackEnd.ClinicaOdontologica;

import BackEnd.ClinicaOdontologica.entity.Domicilio;
import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.service.PacienteService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacientesIntegracionTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private void cargarDatos(){
        Paciente paciente1 = pacienteService.guardarPaciente(new Paciente("Lisa","Simpson","65543321", "lisa@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
        Paciente paciente2 = pacienteService.guardarPaciente(new Paciente("Marge","Boubier","44333233", "marge@boubier.com", LocalDate.of(2023,6,2),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
    }

    @Test
    public void ListarTodosLosPacientesTest() throws Exception{
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/listartodos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void BuscarPacientePorIdIsOkTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/buscar/id/{id}", 3L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void BuscarPacientePorIdIsNotFoundTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/buscar/id/{id}", 300L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void GuardarPacienteTest() throws Exception{
        Paciente paciente= pacienteService.guardarPaciente(new Paciente("Lisa","Simpson","65543321", "lisa@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
        String pacienteJson = objectMapper.writeValueAsString(paciente);
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(pacienteJson, respuesta.getResponse().getContentAsString());
    }
    @Test
    public void actualizarPacienteTest() throws Exception{
        Paciente paciente= pacienteService.guardarPaciente(new Paciente("Lisa","Simpson","65543321", "lisa@simpson.com", LocalDate.of(2024,6,20),new Domicilio("Av Siempreviva",742,"Springfield","USA")));
        String pacienteJson = objectMapper.writeValueAsString(paciente);
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.put("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals("paciente actualizado con exito", respuesta.getResponse().getContentAsString());
    }
    @Test
    public void EliminarPacienteIsOkTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}", 3L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void EliminarPacienteIsNotFoundTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}", 100L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals("No se encontro el recurso con la Id: 100 en el modelo de Paciente", respuesta.getResponse().getContentAsString());
    }
}

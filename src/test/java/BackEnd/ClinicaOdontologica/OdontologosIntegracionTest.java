package BackEnd.ClinicaOdontologica;

import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.service.OdontologoService;
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
public class OdontologosIntegracionTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private void cargarDatos(){
        Odontologo odontologo1 = odontologoService.guardarOdontologo(new Odontologo("OD11", "Neymar", "Junior"));
        Odontologo odontologo2 = odontologoService.guardarOdontologo(new Odontologo("OD12", "Vinicius", "Junior"));
    }

    @Test
    public void ListarTodosLosOdontologosTest() throws Exception{
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/listartodos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void BuscarOdontologoPorIdIsOkTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 4L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void BuscarOdontologoPorIdIsNotFoundTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 300L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void GuardarOdontologoTest() throws Exception{
        Odontologo odontologo= odontologoService.guardarOdontologo(new Odontologo("OD12", "Vinicius", "Junior"));
        String odontologoJson = objectMapper.writeValueAsString(odontologo);
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(odontologoJson, respuesta.getResponse().getContentAsString());
    }
    @Test
    public void actualizarOdontologoTest() throws Exception{
        Odontologo odontologo= odontologoService.guardarOdontologo(new Odontologo("OD13", "Zlatan", "Ibraimovic"));
        String odontologoJson = objectMapper.writeValueAsString(odontologo);
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.put("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals("ontologo actualizado con exito", respuesta.getResponse().getContentAsString());
    }
    @Test
    public void EliminarOdontologoIsOkTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/{id}", 3L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
    @Test
    public void EliminarOdontologoIsNotFoundTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/{id}", 100L).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals("No se encontro el recurso con la Id: 100 en el modelo de Odontologo", respuesta.getResponse().getContentAsString());
    }
}
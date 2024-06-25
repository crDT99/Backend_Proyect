package BackEnd.ClinicaOdontologica.controller;

import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //para trabajar sin tecnologia de vista
//@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RequestMapping("/pacientes")

public class PacienteController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private PacienteService pacienteService;

    //----------------------- Mapeos -----------------------
    //**** GetMapping
    @PostMapping// guardar un paciente
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/listartodos")// buscar la lista de pacientes
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }
    @GetMapping("/buscar/id/{id}")// buscar paciente por id
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorID(id));
    }
    @GetMapping("/buscar/email/{email}")// buscar paciente por email
    public ResponseEntity<Optional<Paciente>> buscarPacientePorCorreo(@PathVariable("email") String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        return ResponseEntity.ok(pacienteBuscado);
    }

    @DeleteMapping ("/{id}")// eliminar paciente por id
    public ResponseEntity<String> eliminarPacientePorId(@PathVariable("id") Long id){
        pacienteService.eliminarPacientePorId(id);
        return ResponseEntity.ok("Eliminado con éxito");
    }
    @PutMapping// actualizar un paciente
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado con exito");
        }else{
            return  ResponseEntity.badRequest().build();
        }
    }
}

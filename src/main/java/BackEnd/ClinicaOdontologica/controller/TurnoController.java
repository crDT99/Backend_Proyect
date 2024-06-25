package BackEnd.ClinicaOdontologica.controller;


import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.entity.Turno;
import BackEnd.ClinicaOdontologica.service.OdontologoService;
import BackEnd.ClinicaOdontologica.service.PacienteService;
import BackEnd.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@Controller
@RequestMapping("/turnos")

public class TurnoController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    //----------------------- Mapeos -----------------------
    //**** GetMapping
    @GetMapping("/listartodos")// listamos los turnos existentes
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping ("/{id}") // busca turno por id
    public ResponseEntity<Optional<Turno>> buscarTurnoPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }
    //**** PostMapping
    @PostMapping// se crea un nuevo turno
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(turno.getPaciente().getEmail());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorMatricula(turno.getOdontologo().getMatricula());
        if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    //**** DeleteMapping
    @DeleteMapping ("/{id}") // eliminar turno por id
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable("id") Long id){
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurnoPorId(id);
            return ResponseEntity.ok("Turno eliminado con éxito");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //**** PutMapping
    @PutMapping// actualizar un turno
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(turno.getPaciente().getEmail());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(turno.getOdontologo().getMatricula());
        if (turnoBuscado.isPresent() && pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            Turno existeTurno = turnoBuscado.get();
            existeTurno.setPaciente(pacienteBuscado.get());
            existeTurno.setOdontologo(odontologoBuscado.get());
            existeTurno.setFecha(turno.getFecha());
            turnoService.actualizarTurno(existeTurno);
            return ResponseEntity.ok("Turno actualizado con exito");
        } else {
            System.out.println("No encontre nada");
            return ResponseEntity.badRequest().build();
        }
    }
}

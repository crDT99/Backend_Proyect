package BackEnd.ClinicaOdontologica.controller;

import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@Controller
@RequestMapping("/odontologos")
public class OdontologoController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private OdontologoService odontologoService;

    //----------------------- Mapeos -----------------------
    //**** GetMapping
    @GetMapping("/listartodos") //listamos todos los odontologos
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodosOdontologos());
    }
    @GetMapping ("/{id}") // busca odontologo por id
    public ResponseEntity<Optional<Odontologo>> buscarOdontologoPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @GetMapping("/buscar/email/{email}")// buscar paciente por email
    public ResponseEntity<Optional<Odontologo>> buscarOdontologoPorMatricula(@PathVariable("matricula") String matricula){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorMatricula(matricula);
        return ResponseEntity.ok(odontologoBuscado);
    }

    //**** PostMapping
    @PostMapping //crea un nuevo odontologo
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    //**** DeleteMapping
    @DeleteMapping ("/{id}") // eliminar odontologo por id
    public ResponseEntity<String> eliminarOdontologoPorId(@PathVariable("id") Long id){
        odontologoService.eliminarOdontologoPorId(id);
        return ResponseEntity.ok("Eliminado con éxito");
    }

    //**** PutMapping
    @PutMapping// actualizar un odontologo
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("ontologo actualizado con exito");
        }else{
            return  ResponseEntity.badRequest().build();
        }
    }

}
package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.repository.PacienteRepository;
import BackEnd.ClinicaOdontologica.utils.exeptions.EmailNotFoundExeption;
import BackEnd.ClinicaOdontologica.utils.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para operaciones relacionadas con los pacientes.
 */
@Service
@AllArgsConstructor
public class PacienteService {
    @Autowired
    public PacienteRepository pacienteRepository;

    // Guarda un objeto Paciente en la base de datos.
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // Busca un paciente por su ID.
    public Optional<Paciente> buscarPorID(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Paciente"));
        return Optional.ofNullable(pacienteBuscado);
    }

    // Busca un paciente por su dirección de correo electrónico.
    public Optional<Paciente> buscarPorEmail(String email) {
        Paciente pacienteBuscado = pacienteRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundExeption(email, "Paciente"));
        return Optional.ofNullable(pacienteBuscado);
    }

    // Actualiza la información de un paciente existente en la base de datos.
    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    // Busca y devuelve una lista de todos los pacientes registrados en la base de datos.
    public List<Paciente> buscarTodosPacientes() {
        return pacienteRepository.findAll();
    }

    // Elimina un paciente por su ID.
    public void eliminarPacientePorId(Long id) {
        pacienteRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Paciente"));
        pacienteRepository.deleteById(id);
    }
}

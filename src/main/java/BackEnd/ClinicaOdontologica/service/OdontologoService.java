package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Odontologo;
import BackEnd.ClinicaOdontologica.entity.Paciente;
import BackEnd.ClinicaOdontologica.repository.OdontologoRepository;
import BackEnd.ClinicaOdontologica.utils.exeptions.EmailNotFoundExeption;
import BackEnd.ClinicaOdontologica.utils.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Servicio para operaciones relacionadas con los odontólogos.
 */
@Service
@AllArgsConstructor
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    // Guarda un objeto Odontologo en la base de datos.
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    // Busca y devuelve una lista de todos los odontólogos registrados en la base de datos.
    public List<Odontologo> buscarTodosOdontologos() {
        return odontologoRepository.findAll();
    }

    // Elimina un odontólogo por su ID.
    public void eliminarOdontologoPorId(Long id) throws IdNotFoundException {
        odontologoRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Odontologo"));
        odontologoRepository.deleteById(id);
    }

    // Busca un odontólogo por su ID.
    public Optional<Odontologo> buscarPorId(Long id) {
        return Optional.ofNullable(odontologoRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Odontologo")));
    }

    // Busca un odontólogo por su matrícula.
    public Optional<Odontologo> buscarPorMatricula(String matricula) {
        Odontologo odontologoBuscado = odontologoRepository.findByMatricula(matricula).orElseThrow(() -> new EmailNotFoundExeption(matricula, "Paciente"));
        return Optional.ofNullable(odontologoBuscado);
    }

    // Actualiza la información de un odontólogo existente en la base de datos.
    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }
}

package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Turno;
import BackEnd.ClinicaOdontologica.repository.TurnoRepository;
import BackEnd.ClinicaOdontologica.utils.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para operaciones relacionadas con los turnos.
 */
@Service
@AllArgsConstructor
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    // Guarda un objeto Turno en la base de datos.
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    // Busca y devuelve una lista de todos los turnos registrados en la base de datos.
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    // Busca un turno por su ID.
    public Optional<Turno> buscarPorId(Long id) {
        return Optional.ofNullable(turnoRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Turno")));
    }

    // Elimina un turno por su ID.
    public void eliminarTurnoPorId(Long id) throws IdNotFoundException {
        turnoRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Turno"));
        turnoRepository.deleteById(id);
    }

    // Actualiza la información de un turno existente en la base de datos.
    public void actualizarTurno(Turno turno) {
        turnoRepository.save(turno);
    }
}

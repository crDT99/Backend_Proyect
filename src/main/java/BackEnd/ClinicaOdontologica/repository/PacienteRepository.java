package BackEnd.ClinicaOdontologica.repository;

import BackEnd.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de Paciente, extiende de JpaRepository
 */
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByEmail(String email);
}

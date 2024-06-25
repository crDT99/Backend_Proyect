package BackEnd.ClinicaOdontologica.repository;


import BackEnd.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de Turno, extiende de JpaRepository
 */

public interface TurnoRepository extends JpaRepository<Turno,Long> {
}

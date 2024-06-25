package BackEnd.ClinicaOdontologica.repository;

import BackEnd.ClinicaOdontologica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * Repositorio de Usuario, extiende de JpaRepository
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
}

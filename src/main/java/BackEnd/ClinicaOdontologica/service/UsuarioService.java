package BackEnd.ClinicaOdontologica.service;

import BackEnd.ClinicaOdontologica.entity.Usuario;
import BackEnd.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para operaciones relacionadas con los usuarios.
 */
@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Carga los detalles del usuario (autenticación) por su nombre de usuario (correo electrónico).
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("Usuario inexistente: " + username);
        }
    }
}

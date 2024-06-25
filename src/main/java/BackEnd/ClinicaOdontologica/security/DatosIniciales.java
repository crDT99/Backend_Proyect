package BackEnd.ClinicaOdontologica.security;

import BackEnd.ClinicaOdontologica.entity.Usuario;
import BackEnd.ClinicaOdontologica.entity.UsuarioRole;
import BackEnd.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase que inicializa datos en la aplicación al arrancar.
 * Agrega usuarios iniciales con roles y contraseñas cifradas.
 */
@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Contraseñas sin cifrar
        String passSinCifrarUser = "user";
        String passSinCifrarAdm = "admin";

        // Cifrado de contraseñas
        String passCifradoUser = passwordEncoder.encode(passSinCifrarUser);
        String passCifradoAdm = passwordEncoder.encode(passSinCifrarAdm);

        // Creación de usuarios
        Usuario usuarioUser = new Usuario("Joshua", "jtajes", "user@user.com", passCifradoUser, UsuarioRole.ROLE_USER);
        Usuario usuarioAdmin = new Usuario("JoshuaAdmin", "jtajesAdmin", "admin@admin.com", passCifradoAdm, UsuarioRole.ROLE_ADMIN);

        // Guardar usuarios en la base de datos
        Usuario u1 = usuarioRepository.save(usuarioUser);
        Usuario u2 = usuarioRepository.save(usuarioAdmin);

        // Imprimir información
        System.out.println("------ Guardado Inicial ----");
        System.out.println(u1.getAuthorities());
        System.out.println(u2.getAuthorities());
        System.out.println(usuarioRepository.findByEmail(u1.getEmail()));
        System.out.println(usuarioRepository.findByEmail(u2.getEmail()));
    }
}

package BackEnd.ClinicaOdontologica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuración para el cifrado de contraseñas utilizando BCrypt.
 */
@Configuration
public class PasswordEncoder {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12); // Nivel de fuerza de cifrado (4-31)
        //Menor valor (4-6): Ofrece un cifrado más rápido, pero es menos seguro. No se recomienda para aplicaciones críticas de seguridad.
        //Valor medio (10-12): Es un buen equilibrio entre seguridad y rendimiento. Se utiliza comúnmente en aplicaciones web.
        //Mayor valor (24-31): Proporciona un cifrado más seguro, pero también es más lento. Útil para aplicaciones altamente sensibles o que manejan información confidencial.
    }
}

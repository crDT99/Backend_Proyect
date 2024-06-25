package BackEnd.ClinicaOdontologica.security;

import BackEnd.ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity // Esto habilita la seguridad web
public class WebSecurityConfig {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Proveedor de autenticaciones DAO
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService); // Configura el servicio de detalles del usuario
        provider.setPasswordEncoder(bCryptPasswordEncoder); // Configura el cifrado de contraseñas
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/index.html", "/post_turnos.html", "/get_turnos.html").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/*.html").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/index.html", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}

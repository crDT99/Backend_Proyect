package BackEnd.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Clase que representa un usuario en el sistema.
 * Contiene información como nombre, nombre de usuario, email y rol.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del usuario

    @Column(nullable = false)
    @NonNull private String nombre; // Nombre del usuario
    @Column(nullable = false)
    @NonNull private String userName; // Nombre de usuario
    @Column(nullable = false)
    @NonNull private String email; // Email del usuario
    @Column(nullable = false)
    @NonNull private String password; // Contraseña del usuario

    @Enumerated(EnumType.STRING)
    @NonNull private UsuarioRole usuarioRole; // Rol del usuario

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(usuarioRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

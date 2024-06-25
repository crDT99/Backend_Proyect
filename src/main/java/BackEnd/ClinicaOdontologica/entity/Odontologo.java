package BackEnd.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Clase que representa un Odontologo en el sistema.
 * Contiene información como  matricula, nombre y apellido.
 */
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @NonNull private String matricula;
    @Column @NonNull private String nombre;
    @Column @NonNull private String apellido;
}
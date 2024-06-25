package BackEnd.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Clase que representa un domicilio en el sistema.
 * Contiene información como calle, número, localidad y provincia.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor //Constructor sin id
@Entity
@Builder
@Table(name = "domicilio")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del domicilio

    @Column @NonNull private String calle;
    @Column @NonNull private Integer numero;
    @Column @NonNull private String localidad;
    @Column @NonNull private String provincia;

}

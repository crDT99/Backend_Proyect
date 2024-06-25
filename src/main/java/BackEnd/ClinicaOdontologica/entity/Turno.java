package BackEnd.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Clase que representa un turno en el sistema.
 * Contiene información como la fecha, el paciente y el odontólogo.
 */
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del turno

    @Column(nullable = false)
    @NonNull private LocalDate fecha; // Fecha del turno

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private @NonNull Paciente paciente; // Paciente asociado al turno

    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private @NonNull Odontologo odontologo; // Odontólogo asociado al turno

}

package BackEnd.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Clase que representa un paciente en el sistema.
 * Contiene información como nombre, apellido, cédula, email y fecha de ingreso.
 */
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del paciente

    @Column(nullable = false)
    @NonNull private String nombre; // Nombre del paciente
    @Column(nullable = false)
    @NonNull private String apellido; // Apellido del paciente
    @Column(nullable = false)
    @NonNull private String cedula; // Cédula del paciente
    @Column(nullable = false)
    @NonNull private String email; // Email del paciente
    @Column(nullable = false)
    @NonNull private LocalDate fechaIngreso; // Fecha de ingreso del paciente

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private @NonNull Domicilio domicilio; // Domicilio del paciente

}
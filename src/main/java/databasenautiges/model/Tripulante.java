package databasenautiges.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "tripulantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tripulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripulante_id")
    private Long id;

    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String tipoDocumento;

    @Column(
        name = "numero_documento",
        length = 20,
        nullable = false,
        unique = true
    )
    private String numeroDocumento;

    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "licencia_navegacion", length = 50)
    private String licenciaNavegacion;

    @Column(name = "fecha_vencimiento_licencia")
    private LocalDate fechaVencimientoLicencia;
}

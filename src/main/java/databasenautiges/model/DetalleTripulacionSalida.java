package databasenautiges.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_tripulacion_salida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleTripulacionSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_id")
    private Long id;

    // RELACIÓN con salida
    @ManyToOne(optional = false)
    @JoinColumn(name = "salida_id", nullable = false)
    private SolicitudSalida salida;

    // RELACIÓN opcional con tripulante
    @ManyToOne
    @JoinColumn(name = "tripulante_id")
    private Tripulante tripulante;

    @Column(name = "nombre_completo", length = 150, nullable = false)
    private String nombreCompleto;

    @Column(name = "es_capitan")
    private Boolean esCapitan;

    // Default
    @PrePersist
    public void prePersist() {
        if (this.esCapitan == null) {
            this.esCapitan = false;
        }
    }
}

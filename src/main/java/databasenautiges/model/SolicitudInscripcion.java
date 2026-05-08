package databasenautiges.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "solicitudes_inscripcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudInscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "clasificacion_externa",
        columnDefinition = "tipo_socio",
        nullable = false
    )
    private TipoSocio clasificacionExterna;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoSolicitud estado;

    @Column(name = "motivo_rechazo", columnDefinition = "TEXT")
    private String motivoRechazo;

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_evaluacion")
    private LocalDateTime fechaEvaluacion;

    @PrePersist
    public void prePersist() {
        this.fechaSolicitud = LocalDateTime.now();

        if (this.estado == null) {
            this.estado = EstadoSolicitud.PENDIENTE;
        }
    }
}

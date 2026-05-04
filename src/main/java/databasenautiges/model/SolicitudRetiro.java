package databasenautiges.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "solicitudes_retiro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudRetiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retiro_id")
    private Long id;

    // RELACIÓN
    @ManyToOne(optional = false)
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "motivo_retiro", columnDefinition = "TEXT")
    private String motivoRetiro;

    @Column(name = "procesada")
    private Boolean procesada;

    // 🔥 Defaults en Java
    @PrePersist
    public void prePersist() {
        if (this.fechaSolicitud == null) {
            this.fechaSolicitud = LocalDate.now();
        }
        if (this.procesada == null) {
            this.procesada = false;
        }
    }
}

package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "solicitudes_salida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salida_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "embarcacion_id", nullable = false)
    private Embarcacion embarcacion;

    @Column(
        name = "itinerario_viaje",
        columnDefinition = "TEXT",
        nullable = false
    )
    private String itinerarioViaje;

    @Column(name = "fecha_partida", nullable = false)
    private LocalDateTime fechaPartida;

    @Column(name = "fecha_retorno", nullable = false)
    private LocalDateTime fechaRetorno;

    // ENUM
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoSalida estado;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    //Default
    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = EstadoSalida.PENDIENTE;
        }
    }

    //VALIDACIÓN equivalente al CHECK
    @AssertTrue(
        message = "La fecha de retorno debe ser posterior a la de partida"
    )
    public boolean isFechasValidas() {
        if (fechaPartida == null || fechaRetorno == null) return true;
        return fechaRetorno.isAfter(fechaPartida);
    }
}

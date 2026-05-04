package databasenautiges.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "socios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socio_id")
    private Long id;

    // RELACIÓN 1 a 1
    @OneToOne(optional = false)
    @JoinColumn(name = "persona_id", nullable = false, unique = true)
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_socio")
    private EstadoSocio estadoSocio;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_retiro")
    private LocalDate fechaRetiro;

    @PrePersist
    public void prePersist() {
        if (this.estadoSocio == null) {
            this.estadoSocio = EstadoSocio.ACTIVO;
        }
        if (this.fechaIngreso == null) {
            this.fechaIngreso = LocalDate.now();
        }
    }
}

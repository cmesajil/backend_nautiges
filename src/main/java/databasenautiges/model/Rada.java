package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "radas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rada_id")
    private Long id;

    @Column(name = "codigo_rada", length = 20, nullable = false, unique = true)
    private String codigoRada;

    @Column(name = "dimensiones", length = 50, nullable = false)
    private String dimensiones;

    @Column(name = "caracteristicas_soporte", length = 100)
    private String caracteristicasSoporte;

    @Column(
        name = "tarifa_mensual",
        precision = 10,
        scale = 2,
        nullable = false
    )
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal tarifaMensual;

    @Column(name = "disponible")
    private Boolean disponible;

    @PrePersist
    public void prePersist() {
        if (this.disponible == null) {
            this.disponible = true;
        }
    }
}

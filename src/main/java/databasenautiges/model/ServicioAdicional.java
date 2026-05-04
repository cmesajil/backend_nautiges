package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "servicios_adicionales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private Long id;

    @Column(name = "nombre_servicio", length = 100, nullable = false)
    private String nombreServicio;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tarifa_base", precision = 10, scale = 2, nullable = false)
    @DecimalMin(
        value = "0.00",
        message = "La tarifa base no puede ser negativa"
    )
    private BigDecimal tarifaBase;
}

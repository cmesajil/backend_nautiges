package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Entity //fila de la base de datos cada vez que crees un objeto
@Table(
    name = "cuotas_comprobante",
    uniqueConstraints = @UniqueConstraint(
        columnNames = { "comprobante_id", "numero_cuota" }
    )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuotaComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuota_id")
    private Long id;

    // RELACIÓN
    @ManyToOne(optional = false)
    @JoinColumn(name = "comprobante_id", nullable = false)
    private Comprobante comprobante;

    @Column(name = "numero_cuota", nullable = false)
    @Min(value = 1, message = "El número de cuota debe ser >= 1")
    private Integer numeroCuota;

    @Column(name = "monto_cuota", precision = 10, scale = 2, nullable = false)
    @DecimalMin(
        value = "0.01",
        message = "El monto de la cuota debe ser mayor a 0"
    )
    private BigDecimal montoCuota;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago")
    private EstadoPago estadoPago;

    // 🔥 Defaults
    @PrePersist
    public void prePersist() {
        if (this.estadoPago == null) {
            this.estadoPago = EstadoPago.PENDIENTE;
        }
    }
}

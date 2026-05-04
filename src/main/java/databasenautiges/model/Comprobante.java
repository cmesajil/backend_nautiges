package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comprobante_id")
    private Long id;

    // Relación con Socio
    @ManyToOne(optional = false)
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    @OneToMany(mappedBy = "comprobante")
    private List<DetalleComprobante> detalles;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL)
    private List<CuotaComprobante> cuotas;

    // ENUM
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    @Column(name = "serie", length = 10, nullable = false)
    private String serie;

    @Column(name = "numero", length = 20, nullable = false)
    private String numero;

    // Montos
    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "Subtotal no puede ser negativo")
    private BigDecimal subtotal;

    @Column(name = "interes_mora", precision = 10, scale = 2)
    @DecimalMin(value = "0.00", message = "Interés no puede ser negativo")
    private BigDecimal interesMora;

    @Column(name = "igv", precision = 10, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "IGV no puede ser negativo")
    private BigDecimal igv;

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "Total no puede ser negativo")
    private BigDecimal total;

    // Fechas
    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    // Estado
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago")
    private EstadoPago estadoPago;

    @Column(name = "pago_diferido")
    private Boolean pagoDiferido;

    @Column(name = "numero_cuotas_max")
    @Max(value = 6, message = "Máximo 6 cuotas")
    private Integer numeroCuotasMax;

    //  Defaults en Java
    @PrePersist
    public void prePersist() {
        if (this.interesMora == null) {
            this.interesMora = BigDecimal.ZERO;
        }
        if (this.estadoPago == null) {
            this.estadoPago = EstadoPago.PENDIENTE;
        }
        if (this.pagoDiferido == null) {
            this.pagoDiferido = false;
        }
        if (this.numeroCuotasMax == null) {
            this.numeroCuotasMax = 1;
        }
        if (this.fechaEmision == null) {
            this.fechaEmision = LocalDate.now();
        }
    }

    // Validación lógica
    @AssertTrue(
        message = "La fecha de vencimiento debe ser posterior o igual a la de emisión"
    )
    public boolean isFechasValidas() {
        if (fechaEmision == null || fechaVencimiento == null) return true;
        return !fechaVencimiento.isBefore(fechaEmision);
    }

    @AssertTrue(
        message = "El subtotal debe coincidir con la suma de los detalles"
    )
    public boolean isSubtotalValido() {
        if (detalles == null || subtotal == null) return true;

        BigDecimal suma = detalles
            .stream()
            .map(DetalleComprobante::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return subtotal.compareTo(suma) == 0;
    }
}

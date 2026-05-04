package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "detalle_comprobante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_comp_id")
    private Long id;

    // RELACIÓN
    @ManyToOne(optional = false)
    @JoinColumn(name = "comprobante_id", nullable = false)
    private Comprobante comprobante;

    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "El monto no puede ser negativo")
    private BigDecimal monto;
}

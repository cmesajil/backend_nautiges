package databasenautiges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "embarcaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Embarcacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "embarcacion_id")
    private Long id;

    //  MUCHAS embarcaciones → UN socio
    @ManyToOne(optional = false)
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    @Column(name = "nombre_embarcacion", length = 100, nullable = false)
    private String nombreEmbarcacion;

    @Column(name = "matricula", length = 50, nullable = false, unique = true)
    private String matricula;

    // Medidas
    @Column(name = "eslora", precision = 6, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "La eslora no puede ser negativa")
    private BigDecimal eslora;

    @Column(name = "manga", precision = 6, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "La manga no puede ser negativa")
    private BigDecimal manga;

    @Column(name = "puntal", precision = 6, scale = 2, nullable = false)
    @DecimalMin(value = "0.00", message = "El puntal no puede ser negativo")
    private BigDecimal puntal;

    @Column(name = "datos_verificados")
    private Boolean datosVerificados;

    // Rada opcional
    @OneToOne
    @JoinColumn(name = "rada_id", unique = true)
    private Rada rada;

    @Column(name = "activo")
    private Boolean activo;

    // Defaults en Java
    @PrePersist
    public void prePersist() {
        if (this.datosVerificados == null) {
            this.datosVerificados = false;
        }
        if (this.activo == null) {
            this.activo = true;
        }
    }
}

package databasenautiges.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServicioDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private Boolean disponible;
}

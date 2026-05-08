package databasenautiges.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SolicitudPostulacionDTO {

    // Datos de Persona
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;

    // Datos de la Solicitud
    private String clasificacionExterna;

    // Getters y Setters...
}

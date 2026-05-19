package databasenautiges.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DashboardResumenDTO {

    private Integer totalServicios;
    private Integer totalSocios;
    private List<ServicioDTO> servicios;
}

package databasenautiges.service;

import databasenautiges.dto.DashboardResumenDTO;
import databasenautiges.dto.ServicioDTO;
import databasenautiges.model.ServicioAdicional;
import databasenautiges.repository.ServicioAdicionalRepository;
import databasenautiges.repository.SocioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ServicioAdicionalRepository servicioRepository;
    private final SocioRepository socioRepository;

    public DashboardResumenDTO obtenerResumen() {
        List<ServicioAdicional> serviciosDb = servicioRepository.findAll();

        List<ServicioDTO> servicios = serviciosDb
            .stream()
            .map(servicio ->
                ServicioDTO.builder()
                    .id(servicio.getId())
                    .nombre(servicio.getNombreServicio())
                    .descripcion(servicio.getDescripcion())
                    .precio(servicio.getTarifaBase().doubleValue())
                    .imagen("default.jpg")
                    .disponible(true)
                    .build()
            )
            .toList();

        return DashboardResumenDTO.builder()
            .totalServicios(servicios.size())
            .totalSocios((int) socioRepository.count())
            .servicios(servicios)
            .build();
    }
}

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
    private final databasenautiges.repository.ComprobanteRepository comprobanteRepository;

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

    public List<java.util.Map<String, Object>> getSociosPorEstado() {
        return socioRepository.countSociosByEstado().stream().map(obj -> {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("estado", obj[0] != null ? obj[0].toString() : "DESCONOCIDO");
            map.put("cantidad", obj[1]);
            return map;
        }).toList();
    }

    public List<java.util.Map<String, Object>> getIngresosPorMes() {
        return comprobanteRepository.sumTotalByMonth().stream().map(obj -> {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("year", obj[0]);
            map.put("month", obj[1]);
            map.put("total", obj[2]);
            return map;
        }).toList();
    }
}

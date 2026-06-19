package databasenautiges.controllers;

import databasenautiges.dto.DashboardResumenDTO;
import databasenautiges.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumen")
    public DashboardResumenDTO obtenerResumen() {
        return dashboardService.obtenerResumen();
    }

    @GetMapping("/socios-estado")
    public java.util.List<java.util.Map<String, Object>> getSociosPorEstado() {
        return dashboardService.getSociosPorEstado();
    }

    @GetMapping("/ingresos-mes")
    public java.util.List<java.util.Map<String, Object>> getIngresosPorMes() {
        return dashboardService.getIngresosPorMes();
    }
}

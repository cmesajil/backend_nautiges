package databasenautiges.controllers;

import databasenautiges.dto.SolicitudPostulacionDTO;
import databasenautiges.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "http://localhost:4200") // Para evitar bloqueos de CORS
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService; // Este lo crearemos ahora

    @PostMapping("/postular")
    public ResponseEntity<?> crearPostulacion(
        @RequestBody SolicitudPostulacionDTO dto
    ) {
        try {
            solicitudService.procesarPostulacion(dto);
            return ResponseEntity.ok().body(
                "{\"mensaje\": \"Postulación procesada correctamente\"}"
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                "{\"error\": \"" + e.getMessage() + "\"}"
            );
        }
    }
}

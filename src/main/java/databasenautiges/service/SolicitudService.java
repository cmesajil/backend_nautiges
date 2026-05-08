package databasenautiges.service;

import databasenautiges.dto.SolicitudPostulacionDTO;
import databasenautiges.model.EstadoSolicitud;
import databasenautiges.model.Persona;
import databasenautiges.model.SolicitudInscripcion;
import databasenautiges.model.TipoSocio;
import databasenautiges.repository.PersonaRepository;
import databasenautiges.repository.SolicitudInscripcionRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private SolicitudInscripcionRepository solicitudRepository;

    @Transactional
    public void procesarPostulacion(SolicitudPostulacionDTO dto) {
        // 1. Mapear y guardar la Persona
        Persona persona = new Persona();
        persona.setTipoDocumento(dto.getTipoDocumento());
        persona.setNumeroDocumento(dto.getNumeroDocumento());
        persona.setNombres(dto.getNombres());
        persona.setApellidos(dto.getApellidos());
        persona.setCorreo(dto.getCorreo());
        persona.setTelefono(dto.getTelefono());
        persona.setDireccion(dto.getDireccion());

        persona = personaRepository.save(persona); // Guardamos y obtenemos el ID

        // 2. Mapear y guardar la Solicitud
        SolicitudInscripcion solicitud = new SolicitudInscripcion();
        solicitud.setPersona(persona); // Aquí conectamos ambas tablas
        // Asignamos el valor directamente en el código 🛠️
        // Así no dependemos de lo que venga (o no venga) del DTO
        solicitud.setClasificacionExterna(TipoSocio.PAGADOR);

        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        solicitud.setFechaSolicitud(LocalDateTime.now());

        solicitudRepository.save(solicitud);
    }
}

package databasenautiges.service;

import databasenautiges.dto.SolicitudPostulacionDTO;
import databasenautiges.model.EstadoSolicitud;
import databasenautiges.model.Persona;
import databasenautiges.model.SolicitudInscripcion;
import databasenautiges.model.TipoSocio;
import databasenautiges.repository.PersonaRepository;
import databasenautiges.repository.SolicitudInscripcionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Usa Mockito puro, sin inicializar bases de datos lentas
class SolicitudServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private SolicitudInscripcionRepository solicitudRepository;

    @InjectMocks
    private SolicitudService solicitudService; // Inyecta automáticamente los mocks de arriba aquí

    @Test
    void cuandoProcesarPostulacion_entoncesGuardaPersonaYSolicitudCorrectamente() {
        // 1. GIVEN (Preparar datos simulados)
        SolicitudPostulacionDTO dto = SolicitudPostulacionDTO.builder()
            .tipoDocumento("DNI")
            .numeroDocumento("12345678")
            .nombres("Linus")
            .apellidos("Torvalds")
            .correo("linus@linux.org")
            .telefono("999888777")
            .direccion("Calle Kernel 123")
            .build();

        // Simulamos que al guardar cualquier Persona, la BD retorna esa persona con un ID ficticio (ej: 42)
        Mockito.when(
            personaRepository.save(Mockito.any(Persona.class))
        ).thenAnswer(invocation -> {
            Persona p = invocation.getArgument(0);
            p.setId(42L); // Le seteamos un ID simulado como haría la base de datos real
            return p;
        });

        // Simulamos el guardado de la solicitud para que no haga nada (solo verifique que se llame)
        Mockito.when(
            solicitudRepository.save(Mockito.any(SolicitudInscripcion.class))
        ).thenAnswer(invocation -> invocation.getArgument(0));

        // 2. WHEN (Ejecutar la lógica del servicio)
        solicitudService.procesarPostulacion(dto);

        // 3. THEN (Verificar que el servicio interactuó correctamente con los repositorios)

        // Verificamos que se haya intentado guardar la persona con los datos correctos del DTO
        Mockito.verify(personaRepository).save(
            Mockito.argThat(
                persona ->
                    "12345678".equals(persona.getNumeroDocumento()) &&
                    "Linus".equals(persona.getNombres()) &&
                    "Torvalds".equals(persona.getApellidos())
            )
        );

        // Verificamos que se haya guardado la solicitud vinculada a la persona con ID 42 y las reglas de negocio
        Mockito.verify(solicitudRepository).save(
            Mockito.argThat(
                solicitud ->
                    solicitud.getPersona() != null &&
                    Long.valueOf(42L).equals(solicitud.getPersona().getId()) && // ¡Verifica que capturó el ID!
                    TipoSocio.PAGADOR.equals(
                        solicitud.getClasificacionExterna()
                    ) &&
                    EstadoSolicitud.PENDIENTE.equals(solicitud.getEstado()) &&
                    solicitud.getFechaSolicitud() != null
            )
        );
    }
}

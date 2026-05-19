package databasenautiges.config;

import databasenautiges.model.Persona;
import databasenautiges.model.Role;
import databasenautiges.model.ServicioAdicional;
import databasenautiges.model.Usuario;
import databasenautiges.repository.PersonaRepository;
import databasenautiges.repository.ServicioAdicionalRepository;
import databasenautiges.repository.UsuarioRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final ServicioAdicionalRepository servicioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // =========================
        // USUARIO ADMIN
        // =========================

        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Persona persona = personaRepository
                .findByNumeroDocumento("00000001")
                .orElseGet(() -> {
                    Persona nuevaPersona = Persona.builder()
                        .nombres("Admin")
                        .apellidos("Sistema")
                        .correo("admin@poseidon.com")
                        .numeroDocumento("00000001")
                        .telefono("999999999")
                        .direccion("Sistema")
                        .tipoDocumento("DNI")
                        .build();

                    return personaRepository.save(nuevaPersona);
                });

            Usuario usuario = Usuario.builder()
                .username("admin")
                .passwordHash(passwordEncoder.encode("123456"))
                .role(Role.ROLE_ADMIN)
                .activo(true)
                .persona(persona)
                .build();

            usuarioRepository.save(usuario);

            System.out.println("Usuario admin creado");
        }

        // =========================
        // SERVICIOS DEMO
        // =========================

        if (servicioRepository.count() == 0) {
            servicioRepository.save(
                ServicioAdicional.builder()
                    .nombreServicio("Alquiler de Yates")
                    .descripcion(
                        "Servicio premium de alquiler de yates de lujo."
                    )
                    .tarifaBase(new BigDecimal("1500.00"))
                    .build()
            );

            servicioRepository.save(
                ServicioAdicional.builder()
                    .nombreServicio("Mantenimiento Naval")
                    .descripcion(
                        "Mantenimiento preventivo y correctivo de embarcaciones."
                    )
                    .tarifaBase(new BigDecimal("850.00"))
                    .build()
            );

            servicioRepository.save(
                ServicioAdicional.builder()
                    .nombreServicio("Escuela de Navegación")
                    .descripcion("Cursos certificados de navegación marítima.")
                    .tarifaBase(new BigDecimal("500.00"))
                    .build()
            );

            servicioRepository.save(
                ServicioAdicional.builder()
                    .nombreServicio("Renta de Radas")
                    .descripcion(
                        "Espacios seguros para amarre de embarcaciones."
                    )
                    .tarifaBase(new BigDecimal("1200.00"))
                    .build()
            );

            System.out.println("Servicios demo creados");
        }
    }
}

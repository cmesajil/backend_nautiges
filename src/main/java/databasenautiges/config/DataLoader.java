package databasenautiges.config;

import databasenautiges.model.Persona;
import databasenautiges.model.Role;
import databasenautiges.model.Usuario;
import databasenautiges.repository.PersonaRepository;
import databasenautiges.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Persona persona;

            // Verificar si la persona ya existe
            persona = personaRepository
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
    }
}

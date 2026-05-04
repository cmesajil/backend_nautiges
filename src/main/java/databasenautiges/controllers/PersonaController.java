package databasenautiges.controllers;

import databasenautiges.model.Persona;
import databasenautiges.repository.PersonaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaRepository repo;

    public PersonaController(PersonaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Persona> listar() {
        return repo.findAll();
    }

    @GetMapping("/test")
    public Persona test() {
        Persona p = Persona.builder()
            .nombres("cristian2")
            .apellidos("mesajil2")
            .tipoDocumento("DNI")
            .numeroDocumento("75436772")
            .correo("correo2")
            .telefono("telefono2")
            .fechaRegistro(LocalDateTime.now())
            .build();
        return repo.save(p);

        // Persona p = new Persona();
        // p.setNombres("TEST2");
        // p.setApellidos("TEST2");
        // p.setDni("000000002");
        // p.setCorreo("test2@test.com");
        // return repo.save(p);

        // Persona p2 = new Persona(
        //     null, // ⚠️ importante
        //     "cristian",
        //     "mesajil",
        //     "7543677",
        //     "cristian@mesajil.com",
        //     "432526",
        //     LocalDateTime.now()
        // );
        // return repo.save(p2);

        // Persona p3 = new Persona();
        // return repo.save(p3);
    }
}

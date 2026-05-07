package databasenautiges.controllers;

import databasenautiges.dto.AuthResponseDTO;
import databasenautiges.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());

        String token = "mi-token-falso";

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}

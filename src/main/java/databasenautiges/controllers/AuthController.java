package databasenautiges.controllers;

import databasenautiges.dto.AuthResponseDTO;
import databasenautiges.dto.LoginRequestDTO;
import databasenautiges.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());

        return ResponseEntity.ok(authService.login(request));
    }
}

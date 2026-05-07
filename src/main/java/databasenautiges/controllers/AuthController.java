package databasenautiges.controllers;

import databasenautiges.dto.LoginRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        return "login recibido";
    }
}

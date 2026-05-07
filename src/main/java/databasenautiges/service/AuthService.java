package databasenautiges.service;

import databasenautiges.dto.AuthResponseDTO;
import databasenautiges.dto.LoginRequestDTO;
import databasenautiges.model.Persona;
import databasenautiges.model.Socio;
import databasenautiges.model.Usuario;
import databasenautiges.repository.PersonaRepository;
import databasenautiges.repository.UsuarioRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtService;

    //se tiene una cuenta existente y se desea loguerse
    public AuthResponseDTO login(LoginRequestDTO request) {
        var usuario = usuarioRepository
            .findByUsername(request.getUsername())
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado")
            );

        if (
            !passwordEncoder.matches(
                request.getPassword(),
                usuario.getPasswordHash()
            )
        ) throw new BadCredentialsException("Contraseña incorrecta");

        var token = jwtService.generateToken(usuario);
        return AuthResponseDTO.builder().token(token).build();
    }
}

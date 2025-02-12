package br.com.projetos.sorteadorDuplasBT.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetos.sorteadorDuplasBT.dto.LoginRequest;
import br.com.projetos.sorteadorDuplasBT.model.Role;
import br.com.projetos.sorteadorDuplasBT.model.Usuario;
import br.com.projetos.sorteadorDuplasBT.repository.UsuarioRepository;
import br.com.projetos.sorteadorDuplasBT.security.JwtTokenProvider;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
   
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole(Role.USER); // Por padrão, todo usuário novo será USER
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String token = jwtTokenProvider.generateToken(authentication);
            
            Map<String, String> response = new HashMap();
            response.put("token", token);
            response.put("role", "teste");
            response.put("message", "Login realizado com sucesso");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Usuário ou senha inválidos"));
        }
    }
}

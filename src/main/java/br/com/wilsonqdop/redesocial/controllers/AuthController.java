package br.com.wilsonqdop.redesocial.controllers;

import br.com.wilsonqdop.redesocial.domain.user.User;
import br.com.wilsonqdop.redesocial.domain.user.userdto.LoginRequestDTO;
import br.com.wilsonqdop.redesocial.domain.user.userdto.ResponseDTO;
import br.com.wilsonqdop.redesocial.domain.user.userdto.RegisterRequestDTO;
import br.com.wilsonqdop.redesocial.infra.TokenService;
import br.com.wilsonqdop.redesocial.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO login) {
        User user = this.userRepository.findByEmail(login.email()).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));

        if (passwordEncoder.matches(login.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO register) {
        Optional<User> user = this.userRepository.findByEmail(register.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(register.password()));
            newUser.setEmail(register.email());
            newUser.setName(register.name());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}

package com.example.auth_service.controller;

import com.example.auth_service.dto.AuthResponse;
import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.MeResponse;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "auth-service is alive";
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
                return ResponseEntity.ok(authService.login(loginRequest.username,  loginRequest.password));

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        authService.register(
                request
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/me")
    public ResponseEntity<MeResponse> me(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().toString();
        return ResponseEntity.ok(new MeResponse(username,role));
    }
}

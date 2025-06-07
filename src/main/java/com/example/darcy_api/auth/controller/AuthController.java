package com.example.darcy_api.auth.controller;

import com.example.darcy_api.auth.dto.AuthRequestDTO;
import com.example.darcy_api.auth.dto.AuthResponseDTO;
import com.example.darcy_api.auth.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/professor")
    public ResponseEntity<AuthResponseDTO> professor(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.loginProfessor(authRequestDTO));
    }

    @PostMapping("/student")
    public ResponseEntity<AuthResponseDTO> student(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.loginStudent(authRequestDTO));
    }
}

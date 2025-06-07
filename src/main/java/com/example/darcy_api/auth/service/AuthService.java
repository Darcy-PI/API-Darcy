package com.example.darcy_api.auth.service;

import com.example.darcy_api.auth.dto.AuthRequestDTO;
import com.example.darcy_api.auth.dto.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO loginProfessor(AuthRequestDTO authRequestDTO);

    AuthResponseDTO loginStudent(AuthRequestDTO authRequestDTO);

}

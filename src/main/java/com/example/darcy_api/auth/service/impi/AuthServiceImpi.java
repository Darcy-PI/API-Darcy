package com.example.darcy_api.auth.service.impi;

import com.example.darcy_api.auth.dto.AuthRequestDTO;
import com.example.darcy_api.auth.dto.AuthResponseDTO;
import com.example.darcy_api.auth.service.AuthService;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.repository.ProfessorRepository;
import com.example.darcy_api.repository.StudentRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpi implements AuthService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpi(ProfessorRepository professorRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO loginProfessor(AuthRequestDTO authRequestDTO) {
        Professor professor = professorRepository.getByUsuario(authRequestDTO.getUsuario());
        if (professor == null || !passwordEncoder.matches(authRequestDTO.getSenha(), professor.getSenha()))
            throw new BadCredentialsException("Usuário ou senha incorretos");

        return new AuthResponseDTO(true, "PROFESSOR", professor.getUsuario());
    }

    @Override
    public AuthResponseDTO loginStudent(AuthRequestDTO authRequestDTO) {
        Student student = studentRepository.getByUsuario(authRequestDTO.getUsuario());
        if (student == null || !passwordEncoder.matches(authRequestDTO.getSenha(), student.getSenha()))
            throw new BadCredentialsException("Usuário ou senha incorretos");

        return new AuthResponseDTO(true, "STUDENT", student.getUsuario());
    }
}

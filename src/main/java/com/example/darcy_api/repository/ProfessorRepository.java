package com.example.darcy_api.repository;

import com.example.darcy_api.model.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

    Professor getByUsuario(
            @NotBlank(message = "usuário não pode estar vazio")
            @Size(max = 15)
            String usuario
    );
}

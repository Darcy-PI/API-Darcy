package com.example.darcy_api.repository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.darcy_api.model.Student;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {


    Student getByUsuario(
            @NotBlank(message = "usuário não pode estar vazio")
            @Size(max = 15)
            String usuario
    );
}

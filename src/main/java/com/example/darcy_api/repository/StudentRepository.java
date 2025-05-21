package com.example.darcy_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.darcy_api.model.Aluno;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    
    
}

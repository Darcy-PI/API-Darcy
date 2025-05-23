package com.example.darcy_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.darcy_api.model.Student;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    
    
}

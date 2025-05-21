package com.example.darcy_api.repository;

import com.example.darcy_api.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

}

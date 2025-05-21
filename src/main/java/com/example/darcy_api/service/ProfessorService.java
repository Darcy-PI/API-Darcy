package com.example.darcy_api.service;

import com.example.darcy_api.model.Professor;

import java.util.List;
import java.util.UUID;

public interface ProfessorService {

    List<Professor> getAllProfessors();

    Professor getProfessorById(UUID id);

    Professor createProfessor(Professor professor);

    Professor updateProfessor(Professor professor);

    void deleteProfessor(UUID id);
}

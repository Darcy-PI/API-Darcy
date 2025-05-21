package com.example.darcy_api.service.impi;

import com.example.darcy_api.model.Professor;
import com.example.darcy_api.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> findAllProfessor() {
        return professorRepository.findAll();
    }

    public Professor findProfessorById(UUID id) {
        return professorRepository.findById(id).orElse(null);
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(UUID id, Professor professor) {
        return professorRepository.findById(id).orElse(professor);
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }

}

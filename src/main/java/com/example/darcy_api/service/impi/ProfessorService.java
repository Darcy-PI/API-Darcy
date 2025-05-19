package com.example.darcy_api.service;

import com.example.darcy_api.model.ProfessorModel;
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

    public List<ProfessorModel> findAllProfessor() {
        return professorRepository.findAll();
    }

    public ProfessorModel findProfessorById(UUID id) {
        return professorRepository.findById(id).orElse(null);
    }

    public ProfessorModel createProfessor(ProfessorModel professor) {
        return professorRepository.save(professor);
    }

    public ProfessorModel updateProfessor(UUID id, ProfessorModel professor) {
        return professorRepository.findById(id).orElse(professor);
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }

}

package com.example.darcy_api.service;

import org.springframework.stereotype.Service;
import com.example.darcy_api.repository.AlunoRepository;
import com.example.darcy_api.model.AlunoModel;
import java.util.UUID;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }


    public AlunoModel getAlunoById(UUID id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public AlunoModel createAluno(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    public AlunoModel updateAluno(UUID id, AlunoModel newAluno) {
        AlunoModel existingAluno = alunoRepository.findById(id).orElse(null);
        if (existingAluno != null) {
            return alunoRepository.save(existingAluno);
        }
        return null;
    }

    public void deleteAluno(UUID id) {
        alunoRepository.deleteById(id);
    }
}

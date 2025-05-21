package com.example.darcy_api.service;

import org.springframework.stereotype.Service;
import com.example.darcy_api.repository.StudentRepository;
import com.example.darcy_api.model.Student;
import java.util.UUID;

@Service
public class AlunoService {
    private final StudentRepository alunoRepository;
    public AlunoService(StudentRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }


    public Student getAlunoById(UUID id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Student createAluno(Student aluno) {
        return alunoRepository.save(aluno);
    }

    public Student updateAluno(UUID id, Student newAluno) {
        Student existingAluno = alunoRepository.findById(id).orElse(null);
        if (existingAluno != null) {
            return alunoRepository.save(existingAluno);
        }
        return null;
    }

    public void deleteAluno(UUID id) {
        alunoRepository.deleteById(id);
    }
}

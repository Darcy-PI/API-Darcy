package com.example.darcy_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.darcy_api.repository.AlunoRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.example.darcy_api.model.AlunoModel;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@RestController("/api/v1/aluno")
public class AlunoController {
    
    private AlunoRepository alunoRepository;
    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> getAllAlunos(){
        List<AlunoModel> alunos = alunoRepository.findAll();
        if(alunos.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(alunos);
    }

    @GetMapping
    public ResponseEntity<AlunoModel> getAlunoById(UUID id){
        AlunoModel aluno = alunoRepository.findById(id).orElse(null);
        if(aluno == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoModel> createAluno(AlunoModel aluno){
        AlunoModel savedAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(201).body(savedAluno);
    }


    @PutMapping
    public ResponseEntity<AlunoModel> updateAluno(@PathVariable UUID id, @RequestBody AlunoModel Newaluno){
        AlunoModel aluno = alunoRepository.findById(id).orElse(null);
        if(aluno == null){
            return ResponseEntity.notFound().build();
        }
        AlunoModel updatedAluno = alunoRepository.save(Newaluno);
        return ResponseEntity.ok(updatedAluno);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAluno(@PathVariable UUID id){
        AlunoModel aluno = alunoRepository.findById(id).orElse(null);
        if(aluno == null){
            return ResponseEntity.notFound().build();
        }
        alunoRepository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
        
}

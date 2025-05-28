package com.example.darcy_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.darcy_api.repository.StudentRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.example.darcy_api.model.Student;
import java.util.UUID;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@RestController
@RequestMapping("/api/v1/alunos")
public class StudentController {
    
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllAlunos(){
        List<Student> alunos = studentRepository.findAll();
        if(alunos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }

    @GetMapping
    public ResponseEntity<Student> getAlunoById(UUID id){
        Student aluno = studentRepository.findById(id).orElse(null);
        if(aluno == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Student> createAluno(Student aluno){
        Student savedAluno = studentRepository.save(aluno);
        return ResponseEntity.status(201).body(savedAluno);
    }


    @PutMapping
    public ResponseEntity<Student> updateAluno(@PathVariable UUID id, @RequestBody Student Newaluno){
        Student aluno = studentRepository.findById(id).orElse(null);
        if(aluno == null){
            return ResponseEntity.notFound().build();
        }
        Student updatedAluno = studentRepository.save(Newaluno);
        return ResponseEntity.ok(updatedAluno);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAluno(@PathVariable UUID id){
        Student aluno = studentRepository.findById(id).orElse(null);
        if(aluno == null){
            return ResponseEntity.notFound().build();
        }
        studentRepository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
        
}

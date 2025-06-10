package com.example.darcy_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.darcy_api.service.StudentService;

import jakarta.validation.Valid;

import com.example.darcy_api.dto.update.StudentUpdateDTO;
import com.example.darcy_api.model.Student;

import java.util.UUID;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents(){
        List<Student> alunosList = studentService.getAllStudents();
        if (alunosList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", alunosList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable UUID id){
        Student student = studentService.getStudentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", student);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(Student aluno){
        Student savedStudent = studentService.createStudent(aluno);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", savedStudent);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable UUID id, @Valid @RequestBody StudentUpdateDTO studentUpdateDTO){
        Student updatedStudent = studentService.updateStudentById(id, studentUpdateDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", updatedStudent);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable UUID id){
        studentService.deleteStudentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Estudante deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}

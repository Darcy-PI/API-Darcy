package com.example.darcy_api.controller;

import com.example.darcy_api.model.Professor;
import com.example.darcy_api.service.impi.ProfessorService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RestController("/api/v1/professor")
public class ProfessorController {
    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAllProfessor() {
        List<Professor> professoresList = professorService.findAllProfessor();
        return ResponseEntity.ok(professoresList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findProfessorById(@PathVariable UUID id) {
        Professor professor = professorService.findProfessorById(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor newProfessor = professorService.createProfessor(professor);
        return ResponseEntity.ok(newProfessor);
    }

    @PutMapping("{/id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable UUID id , @RequestBody Professor professor) {
        Professor updateProfessor = professorService.updateProfessor(id, professor);
        return ResponseEntity.ok(updateProfessor);
    }

    @DeleteMapping
    public String deleteProfessor(@PathVariable UUID id) {
        professorService.deleteProfessor(id);
        return "Professor deleted";
    }





}

package com.example.darcy_api.controller;

import com.example.darcy_api.model.ProfessorModel;
import com.example.darcy_api.repository.ProfessorRepository;
import com.example.darcy_api.service.ProfessorService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ProfessorModel>> findAllProfessor() {
        List<ProfessorModel> professoresList = professorService.findAllProfessor();
        return ResponseEntity.ok(professoresList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorModel> findProfessorById(@PathVariable UUID id) {
        ProfessorModel professor = professorService.findProfessorById(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorModel> createProfessor(@RequestBody ProfessorModel professor) {
        ProfessorModel newProfessor = professorService.createProfessor(professor);
        return ResponseEntity.ok(newProfessor);
    }

    @PutMapping("{/id}")
    public ResponseEntity<ProfessorModel> updateProfessor(@PathVariable UUID id , @RequestBody ProfessorModel professor) {
        ProfessorModel updateProfessor = professorService.updateProfessor(id, professor);
        return ResponseEntity.ok(updateProfessor);
    }

    @DeleteMapping
    public String deleteProfessor(@PathVariable UUID id) {
        professorService.deleteProfessor(id);
        return "Professor deleted";
    }





}

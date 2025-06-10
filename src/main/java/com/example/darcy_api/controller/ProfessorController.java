package com.example.darcy_api.controller;

import com.example.darcy_api.dto.request.ProfessorRequestDTO;
import com.example.darcy_api.dto.update.ProfessorUpdateDTO;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.VirtualClassroom;
import com.example.darcy_api.service.ProfessorService;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProfessor() {
        List<Professor> professoresList = professorService.getAllProfessors();
        if (professoresList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", professoresList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProfessorById(@PathVariable UUID id) {
        Professor professor = professorService.getProfessorById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", professor);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/virtualClasses/{id}")
    public ResponseEntity<Map<String, Object>> getAllProfessorVirtualClassesById(@PathVariable UUID id){
        List<VirtualClassroom> ambientes = professorService.getAllProfessorVirtualClassroomsById(id);
        if (ambientes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", ambientes);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProfessor(@Valid @RequestBody ProfessorRequestDTO professorRequestDTO) {
        Professor createdProfessor = professorService.createProfessor(professorRequestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", createdProfessor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProfessorById(@PathVariable UUID id , @Valid @RequestBody ProfessorUpdateDTO professorUpdateDTO) {
        Professor updateProfessor = professorService.updateProfessorById(id, professorUpdateDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", updateProfessor);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProfessor(@PathVariable UUID id) {
        professorService.deleteProfessorById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Professor deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}

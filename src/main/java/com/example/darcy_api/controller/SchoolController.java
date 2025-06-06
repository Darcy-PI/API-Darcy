package com.example.darcy_api.controller;

import com.example.darcy_api.dto.SchoolUpdateDTO;
import com.example.darcy_api.model.School;
import com.example.darcy_api.service.SchoolService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController{
    private SchoolService schoolService;


    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<Map<String , Object>> getAllSchools(){
        List<School> schools = schoolService.getAllSchools();
        if (schools.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", schools);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable UUID id){
        Student school = schoolService.getStudentById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", school);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSchool(School school){
        Student savedSchool = schoolService.createSchool(school);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", savedSchool);
        return ResponseEntity.status(201).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSchoolById(@PathVariable UUID id, @Valid @RequestBody SchoolUpdateDTO schoolUpdateDTO){
        Student updatedSchool = schoolService.updateSchoolById(id, schoolUpdateDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", updatedSchool);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteSchoolById(@PathVariable UUID id){
        schoolService.deleteSchoolById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Escola deletada com sucesso.");
        return ResponseEntity.ok(response);
    }
}
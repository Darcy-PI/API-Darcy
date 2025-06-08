package com.example.darcy_api.controller;

import com.example.darcy_api.dto.request.StudentDataRequestDTO;
import com.example.darcy_api.dto.response.StudentDataResponseDTO;
import com.example.darcy_api.dto.update.StudentDataUpdateDTO;
import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.service.StudentDataService;
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
@RequestMapping("/api/v1/studentData")
public class StudentDataController {

    private final StudentDataService studentDataService;

    public StudentDataController(StudentDataService studentDataService) {
        this.studentDataService = studentDataService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudentData(){
        List<StudentDataResponseDTO> studentDataList = studentDataService.getAllStudentData();
        if (studentDataList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", studentDataList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/virtualClassroom/{virtualClassroomId}")
    public ResponseEntity<Map<String, Object>> getAllStudentDataByVirtualClassroomId(@PathVariable UUID virtualClassroomId){
        List<StudentDataResponseDTO> studentDataList = studentDataService.getAllStudentDataByVirtualClassroomId(virtualClassroomId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", studentDataList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Map<String, Object>> getAllStudentDataByStudentId(@PathVariable UUID studentId){
        StudentDataResponseDTO studentData = studentDataService.getStudentDataByStudentId(studentId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", studentData);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudentData(@Valid @RequestBody StudentDataRequestDTO studentDataRequestDTO){
        StudentData studentDataCreated = studentDataService.createStudentData(studentDataRequestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", studentDataCreated);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Map<String, Object>> updateStudentData(@PathVariable UUID studentId, @RequestBody StudentDataUpdateDTO studentDataUpdateDTO){
        StudentData updateStudentData = studentDataService.updateStudentDataByStudentId(studentId, studentDataUpdateDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", updateStudentData);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Map<String, Object>> deleteStudentDataByStudentId(@PathVariable UUID studentId){
        studentDataService.deleteStudentDataByStudentId(studentId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "StudentData deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}
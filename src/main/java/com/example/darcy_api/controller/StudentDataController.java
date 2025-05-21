package com.example.darcy_api.controller;

import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.service.StudentDataService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RestController("/api/v1/class/studentData")
public class StudentDataController {

    private final StudentDataService studentDataService;

    public StudentDataController(StudentDataService studentDataService) {
        this.studentDataService = studentDataService;
    }

    @GetMapping
    public ResponseEntity<List<StudentData>> findAllStudentData(){
        List<StudentData> studentDataList = studentDataService.getAllStudents();
        return ResponseEntity.ok(studentDataList);
    }

    @PostMapping
    public ResponseEntity<StudentData> createStudentData(@RequestBody StudentData studentData){
        StudentData studentDataCreated = studentDataService.createStudentData(studentData);
        return ResponseEntity.ok(studentDataCreated);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentDataByStudentId(@PathVariable UUID studentId){
        studentDataService.deleteStudentDataByStudentId(studentId);
        return ResponseEntity.ok("Deleted studentData successfully");
    }
}

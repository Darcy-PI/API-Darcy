package com.example.darcy_api.controller;

import com.example.darcy_api.dto.request.VirtualClassroomRequestDTO;
import com.example.darcy_api.dto.response.VirtualClassroomResponseDTO;
import com.example.darcy_api.dto.update.VirtualClassroomUpdateDTO;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.model.VirtualClassroom;
import com.example.darcy_api.service.VirtualClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/virtualClassrooms")
public class VirtualClassroomController {

    private final VirtualClassroomService virtualClassroomService;

    public VirtualClassroomController(VirtualClassroomService virtualClassroomService) {
        this.virtualClassroomService = virtualClassroomService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllVirtualClassrooms(){
        List<VirtualClassroom> virtualClassrooms = virtualClassroomService.getAllVirtualClassrooms();
        if (virtualClassrooms.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", virtualClassrooms);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getVirtualClassroomById(@PathVariable UUID id){
        VirtualClassroom virtualClassroom = virtualClassroomService.getVirtualClassroomById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", virtualClassroom);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Map<String, Object>> getAllStudentsByVirtualClassroomId(@PathVariable UUID id){
        List<Student> students = virtualClassroomService.getAllStudentsByVirtualClassroomId(id);
        if (students.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", students);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<Map<String, Object>> getAllVirtualClassroomsByProfessorId(@PathVariable UUID professorId){
        List<VirtualClassroomResponseDTO> ambientes = virtualClassroomService.getAllVirtualClassroomsByProfessorId(professorId);
        if (ambientes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", ambientes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Map<String, Object>> getAllVirtualClassroomsByStudentId(@PathVariable UUID studentId){
        List<VirtualClassroomResponseDTO> ambientes = virtualClassroomService.getAllVirtualClassroomsByStudentId(studentId);
        if (ambientes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", ambientes);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/accessKey")
    public ResponseEntity<Map<String, Object>> regenerateAccessKeyByVirtualClassroomId(@PathVariable UUID id){
        virtualClassroomService.regenerateAccessKeyByVirtualClassroomId(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("message", "A chave de acesso foi gerada novamente com sucesso");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/students/{studentId}")
    public ResponseEntity<Map<String, Object>> addStudentToVirtualClassroom(
            @PathVariable UUID studentId,
            @RequestParam String accessKey){
        List<Student> students = virtualClassroomService.addStudentToVirtualClassroom(studentId, accessKey);
        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", students);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createVirtualClassroom(@RequestBody VirtualClassroomRequestDTO virtualClassroomRequestDTO){
        VirtualClassroom savedVirtualClassroom = virtualClassroomService.createVirtualClassroom(virtualClassroomRequestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", savedVirtualClassroom);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateVirtualClassroomById(@PathVariable UUID id, VirtualClassroomUpdateDTO virtualClassroomUpdateDTO){
        VirtualClassroom updatedVirtualClassroom = virtualClassroomService.updateVirtualClassroomById(id, virtualClassroomUpdateDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", updatedVirtualClassroom);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteVirtualClassroomById(@PathVariable UUID id){
        virtualClassroomService.deleteVirtualClassroomById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success",true);
        response.put("data", "Ambiente virtual deletado com sucesso");
        return ResponseEntity.ok(response);
    }
}
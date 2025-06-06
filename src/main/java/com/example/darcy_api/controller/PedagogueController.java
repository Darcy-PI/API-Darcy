package com.example.darcy_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.darcy_api.dto.PedagogueUpdateDTO;
import com.example.darcy_api.model.Pedagogue;
import com.example.darcy_api.service.PedagogueService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
   
@RestController
@RequestMapping("/api/v1/pedagogue")
public class PedagogueController {

    private final PedagogueService pedagogueService;

    public PedagogueController(PedagogueService pedagogueService){
        this.pedagogueService = pedagogueService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPedagogue(@RequestBody Pedagogue pedagogue){
        Pedagogue newPedagogue = pedagogueService.createPedagogue(pedagogue);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", newPedagogue);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPedagogue(){
        List<Pedagogue> pedagogueList = pedagogueService.getAllPedagogues();
        if (pedagogueList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", pedagogueList);
        return ResponseEntity.ok(response);    
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPedagogueById(@PathVariable UUID id){
        Pedagogue pedagogue = pedagogueService.getPedagogueById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", pedagogue);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updatePedagogueById(@PathVariable UUID id, @Valid @RequestBody PedagogueUpdateDTO pedagogueUpdateDTO){
        Pedagogue updatePedagoge = pedagogueService.updatePedagogueById(id, pedagogueUpdateDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", updatePedagoge);
        return ResponseEntity.ok(response); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePedagogueById(@PathVariable UUID id){
        pedagogueService.deletePedagogueById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Pedagogo deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}
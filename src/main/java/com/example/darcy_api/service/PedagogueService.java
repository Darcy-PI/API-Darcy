package com.example.darcy_api.service;

import java.util.List;
import java.util.UUID;

import com.example.darcy_api.dto.update.PedagogueUpdateDTO;
import com.example.darcy_api.model.Pedagogue;

public interface PedagogueService {
    Pedagogue createPedagogue(Pedagogue pedagogue);
    
    List<Pedagogue> getAllPedagogues();
    
    Pedagogue getPedagogueById(UUID id);

    Pedagogue updatePedagogueById(UUID id, PedagogueUpdateDTO pedagogueUpdateDTO);

    void deletePedagogueById(UUID id);
}

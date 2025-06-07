package com.example.darcy_api.service.impi;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.darcy_api.dto.update.PedagogueUpdateDTO;
import com.example.darcy_api.model.Pedagogue;
import com.example.darcy_api.repository.PedagogueRepository;
import com.example.darcy_api.service.PedagogueService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedagogueServiceImpi implements PedagogueService {
    private final PedagogueRepository pedagogueRepository;

    private final PasswordEncoder passwordEncoder;

    public PedagogueServiceImpi(PedagogueRepository pedagogueRepository, PasswordEncoder passwordEncoder) {
        this.pedagogueRepository = pedagogueRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Pedagogue createPedagogue(Pedagogue pedagogue){
        pedagogue.setSenha(passwordEncoder.encode(pedagogue.getSenha()));
        return pedagogueRepository.save(pedagogue);
    }
    
    @Override
    public List<Pedagogue> getAllPedagogues(){
        return pedagogueRepository.findAll();
    }
    
    @Override
    public Pedagogue getPedagogueById(UUID id){
        return pedagogueRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedagogo não encontrado ou inexistente"));
    }

    @Override
    public Pedagogue updatePedagogueById(UUID id, PedagogueUpdateDTO pedagogueUpdateDTO){
        Pedagogue pedagogue = getPedagogueById(id);

        pedagogue.setUsuario(
            pedagogueUpdateDTO.getUsuario() != null ? pedagogueUpdateDTO.getUsuario() : pedagogue.getUsuario());
        pedagogue.setSenha(
            pedagogueUpdateDTO.getSenha() != null ? pedagogueUpdateDTO.getSenha() : pedagogue.getSenha());
        pedagogue.setNomeCompleto(
            pedagogueUpdateDTO.getNomeCompleto() != null ? pedagogueUpdateDTO.getNomeCompleto() : pedagogue.getNomeCompleto());

        return pedagogueRepository.save(pedagogue);
    }

    @Override
    public void deletePedagogueById(UUID id){
        getPedagogueById(id);
        pedagogueRepository.deleteById(id);
    }
}

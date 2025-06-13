package com.example.darcy_api.service.impi;

import com.example.darcy_api.dto.request.ProfessorRequestDTO;
import com.example.darcy_api.dto.update.ProfessorUpdateDTO;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.VirtualClassroom;
import com.example.darcy_api.repository.ProfessorRepository;
import com.example.darcy_api.repository.VirtualClassroomRepository;
import com.example.darcy_api.service.ProfessorService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorServiceImpi implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final VirtualClassroomRepository virtualClassroomRepository;

    private final PasswordEncoder passwordEncoder;

    public ProfessorServiceImpi(ProfessorRepository professorRepository, VirtualClassroomRepository virtualClassroomRepository, PasswordEncoder passwordEncoder) {
        this.professorRepository = professorRepository;
        this.virtualClassroomRepository = virtualClassroomRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public List<VirtualClassroom> getAllProfessorVirtualClassroomsById(UUID id){
        return virtualClassroomRepository.findAllByProfessorDonoId(id);
    }

    @Override
    public Professor getProfessorById(UUID id) {
        return professorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com o id informado não encontrado ou inexistente."));
    }

    @Override
    public Professor createProfessor(ProfessorRequestDTO professorRequestDTO) {
        Professor professor = new Professor();
        professor.setUsuario(professorRequestDTO.getUsuario());
        professor.setNomeCompleto(professorRequestDTO.getNomeCompleto());
        professor.setSenha(passwordEncoder.encode(professorRequestDTO.getSenha()));
        return professorRepository.save(professor);
    }

    @Override
    public Professor updateProfessorById(UUID id, ProfessorUpdateDTO professorUpdateDTO) {
        Professor professor = getProfessorById(id);

        professor.setUsuario(
            professorUpdateDTO.getUsuario() != null ? professorUpdateDTO.getUsuario() : professor.getUsuario());
        professor.setSenha(
            professorUpdateDTO.getSenha() != null ?
                    passwordEncoder.encode(professorUpdateDTO.getSenha()) : professor.getSenha());
        professor.setNomeCompleto(
            professorUpdateDTO.getNomeCompleto() != null ? professorUpdateDTO.getNomeCompleto() : professor.getNomeCompleto());

        return professorRepository.save(professor);
    }

    @Override
    public void deleteProfessorById(UUID id) {
        professorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com o id informado não encontrado ou inexistente."));
        professorRepository.deleteById(id);
    }
}

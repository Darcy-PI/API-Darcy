package com.example.darcy_api.service;

import com.example.darcy_api.dto.update.ProfessorUpdateDTO;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.VirtualClassroom;

import java.util.List;
import java.util.UUID;

public interface ProfessorService {

    List<Professor> getAllProfessors();

    List<VirtualClassroom> getAllProfessorVirtualClassroomsById(UUID id);

    Professor getProfessorById(UUID id);

    Professor createProfessor(Professor professor);

    Professor updateProfessorById(UUID id, ProfessorUpdateDTO professorUpdateDTO);

    void deleteProfessorById(UUID id);
}

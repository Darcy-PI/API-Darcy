package com.example.darcy_api.repository;

import com.example.darcy_api.model.VirtualClassroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VirtualClassroomRepository extends JpaRepository<VirtualClassroom, UUID> {
    List<VirtualClassroom> findAllByProfessorDonoId(UUID professorId);
}

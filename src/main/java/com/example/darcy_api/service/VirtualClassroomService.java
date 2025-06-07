package com.example.darcy_api.service;

import com.example.darcy_api.dto.request.VirtualClassroomRequestDTO;
import com.example.darcy_api.dto.response.VirtualClassroomResponseDTO;
import com.example.darcy_api.dto.update.VirtualClassroomUpdateDTO;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.model.VirtualClassroom;

import java.util.List;
import java.util.UUID;

public interface VirtualClassroomService {

    List<VirtualClassroom> getAllVirtualClassrooms();

    List<VirtualClassroomResponseDTO> getAllVirtualClassroomsByProfessorId(UUID professorId);

    List<VirtualClassroomResponseDTO> getAllVirtualClassroomsByStudentId(UUID studentId);

    VirtualClassroom getVirtualClassroomById(UUID id);

    List<Student> getAllStudentsByVirtualClassroomId(UUID id);

    List<Student> addStudentToVirtualClassroom(UUID id, UUID studentId, String accessKey);

    void regenerateAccessKeyByVirtualClassroomId(UUID id);

    VirtualClassroom createVirtualClassroom(VirtualClassroomRequestDTO virtualClassroomRequestDTO);

    VirtualClassroom updateVirtualClassroomById(UUID id, VirtualClassroomUpdateDTO virtualClassroomUpdateDTO);

    void deleteVirtualClassroomById(UUID id);
}

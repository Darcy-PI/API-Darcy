package com.example.darcy_api.service;

import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.model.VirtualClassroom;

import java.util.List;
import java.util.UUID;

public interface VirtualClassroomService {

    List<VirtualClassroom> getAllVirtualClassrooms();

    VirtualClassroom getVirtualClassroomById(UUID id);

    List<Student> getAllStudentsByVirtualClassroomId(UUID id);

    Professor getCurrentProfessorByVirtualClassroomId(UUID id);

    VirtualClassroom createVirtualClassroom(VirtualClassroom virtualClassroom);

    VirtualClassroom updateVirtualClassroomById(UUID id, VirtualClassroom virtualClassroom);

    void deleteVirtualClassroomById(UUID id);
}

package com.example.darcy_api.service;

import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.model.VirtualClassroom;

import java.util.List;
import java.util.UUID;

public interface VirtualClassroomService {

    List<VirtualClassroom> getAllClassrooms();

    VirtualClassroom getClassroomById(UUID id);

    VirtualClassroom createClassroom(VirtualClassroom virtualClassroom);

    VirtualClassroom updateVirtualClassroomById(UUID id, VirtualClassroom virtualClassroom);

    void deleteVirtualClassroomById(UUID id);

//    List<Student> getAllStudentsByVirtualClassroomId(UUID id);

    List<StudentData> getAllStudentDataByVirtualClassroomId(UUID id);
}

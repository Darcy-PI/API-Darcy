package com.example.darcy_api.service;

import com.example.darcy_api.dto.StudentUpdateDTO;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.model.VirtualClassroom;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(UUID id);

    List<VirtualClassroom> getAllStudentVirtualClassroomsByStudentId(UUID id);

    Student addVirtualClassroomToStudent(UUID id, UUID virtualClassroomId);

    Student createStudent(Student student);

    Student updateStudentById(UUID id, StudentUpdateDTO studentUpdateDTO);

    void deleteStudentById(UUID id);
}

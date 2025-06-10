package com.example.darcy_api.service;

import com.example.darcy_api.dto.update.StudentUpdateDTO;
import com.example.darcy_api.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(UUID id);

    Student createStudent(Student student);

    Student updateStudentById(UUID id, StudentUpdateDTO studentUpdateDTO);

    void deleteStudentById(UUID id);
}

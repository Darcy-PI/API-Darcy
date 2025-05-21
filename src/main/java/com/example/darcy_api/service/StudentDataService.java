package com.example.darcy_api.service;

import com.example.darcy_api.model.StudentData;

import java.util.List;
import java.util.UUID;

public interface StudentDataService {

    List<StudentData> getAllStudents();

    StudentData findStudentDataByStudentId(UUID studentId);

    StudentData createStudentData(StudentData studentData);

    StudentData updateStudentDataById(UUID studentId ,StudentData newStudentData);

    void deleteStudentDataByStudentId(UUID studentId);
}

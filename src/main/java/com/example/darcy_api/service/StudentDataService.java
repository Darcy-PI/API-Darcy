package com.example.darcy_api.service;

import com.example.darcy_api.model.StudentData;

import java.util.List;
import java.util.UUID;

public interface StudentDataService {

    List<StudentData> getAllStudentData();

    List<StudentData> getAllStudentDataByVirtualClassroomId(UUID virtualClassroomId);

    StudentData getStudentDataByStudentId(UUID studentId);

    StudentData createStudentData(StudentData studentData);

    StudentData updateStudentDataByStudentId(UUID studentId, StudentData newStudentData);

    void deleteStudentDataByStudentId(UUID studentId);
}

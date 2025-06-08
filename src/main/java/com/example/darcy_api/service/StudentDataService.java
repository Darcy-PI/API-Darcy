package com.example.darcy_api.service;

import com.example.darcy_api.dto.request.StudentDataRequestDTO;
import com.example.darcy_api.dto.update.StudentDataUpdateDTO;
import com.example.darcy_api.model.StudentData;

import java.util.List;
import java.util.UUID;

public interface StudentDataService {

    List<StudentData> getAllStudentData();

    List<StudentData> getAllStudentDataByVirtualClassroomId(UUID virtualClassroomId);

    StudentData getStudentDataByStudentId(UUID studentId);

    StudentData createStudentData(StudentDataRequestDTO studentDataRequestDTO);

    StudentData updateStudentDataByStudentId(UUID studentId, StudentDataUpdateDTO studentDataUpdateDTO);

    void deleteStudentDataByStudentId(UUID studentId);
}
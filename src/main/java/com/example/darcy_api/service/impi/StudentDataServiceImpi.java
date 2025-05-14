package com.example.darcy_api.service.impi;

import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.repository.StudentDataRepository;
import com.example.darcy_api.service.StudentDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentDataServiceImpi implements StudentDataService {

    private final StudentDataRepository studentDataRepository;

    public StudentDataServiceImpi(StudentDataRepository studentDataRepository) {
        this.studentDataRepository = studentDataRepository;
    }

    @Override
    public List<StudentData> findAllStudentData(){
        List<StudentData> studentDataList = studentDataRepository.findAll();
        return studentDataList;
    }

    @Override
    public StudentData findStudentDataByStudentId(UUID studentId){
        StudentData studentData = studentDataRepository.findByStudentId(studentId);

        return studentData;
    }

    @Override
    public List<StudentData> findAllStudentDataByVirtualClassroomId(UUID virtualClassroomId){
        List<StudentData> studentDataList = studentDataRepository.findAllByVirtualClassroomId(virtualClassroomId);

        return studentDataList;
    }

    @Override
    public StudentData createStudentData(StudentData newStudentData){
        if (studentDataRepository.existsById(newStudentData.getId())) throw new IllegalArgumentException("Esse StudentData já existe");
        studentDataRepository.save(newStudentData);

        return findStudentDataByStudentId(newStudentData.getId());
    }

    @Override
    public StudentData updateStudentData(UUID studentId ,StudentData updateStudentData){
        if (studentDataRepository.existsById(studentId)) throw new IllegalArgumentException("O StudentData buscado não existe");
        studentDataRepository.findByStudentId(studentId);

        return studentDataRepository.save(updateStudentData);
    }

    @Override
    public void deleteStudentDataByStudentId(UUID studentId){
        studentDataRepository.delete(findStudentDataByStudentId(studentId));
    }

}

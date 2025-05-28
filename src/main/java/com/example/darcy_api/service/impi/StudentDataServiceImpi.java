package com.example.darcy_api.service.impi;

import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.repository.StudentDataRepository;
import com.example.darcy_api.repository.StudentRepository;
import com.example.darcy_api.repository.VirtualClassroomRepository;
import com.example.darcy_api.service.StudentDataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentDataServiceImpi implements StudentDataService {

    private final StudentDataRepository studentDataRepository;
    private final StudentRepository studentRepository;
    private final VirtualClassroomRepository virtualClassroomRepository;

    public StudentDataServiceImpi(StudentDataRepository studentDataRepository, VirtualClassroomRepository virtualClassroomRepository, StudentRepository studentRepository) {
        this.studentDataRepository = studentDataRepository;
        this.virtualClassroomRepository = virtualClassroomRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentData> getAllStudentData(){
        return studentDataRepository.findAll();
    }

    @Override
    public List<StudentData> getAllStudentDataByVirtualClassroomId(UUID virtualClassroomId){
        virtualClassroomRepository
                .findById(virtualClassroomId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum ambiente virtual com esse id encontrado.")
                );
        return studentDataRepository.findAllByVirtualClassroomId(virtualClassroomId);
    }

    @Override
    public StudentData getStudentDataByStudentId(UUID studentId){
        StudentData studentData = studentDataRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estudante com respostas registradas com esse id."));
        return studentData;
    }

    @Override
    public StudentData createStudentData(StudentData newStudentData){
        studentRepository
                .findById(newStudentData.getStudent().getId())
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhum estudante com o id informado."));
        virtualClassroomRepository
                .findById(newStudentData.getVirtualClassroom().getId())
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhum ambiente virtual com o id informado."));
        return studentDataRepository.save(newStudentData);
    }

    @Override
    public StudentData updateStudentDataByStudentId(UUID studentId, StudentData updatedStudentData){
        studentDataRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estudante com respostas registradas com esse id."));
        updatedStudentData.setId(studentId);
        return studentDataRepository.save(updatedStudentData);
    }

    @Override
    public void deleteStudentDataByStudentId(UUID studentId){
        StudentData studentData = studentDataRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estudante com respsotas retgistradas com esse id."));
        studentDataRepository.delete(studentData);
    }
}

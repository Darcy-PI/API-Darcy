package com.example.darcy_api.service.impi;

import com.example.darcy_api.dto.request.StudentDataRequestDTO;
import com.example.darcy_api.dto.update.StudentDataUpdateDTO;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.model.StudentData;
import com.example.darcy_api.model.VirtualClassroom;
import com.example.darcy_api.repository.StudentDataRepository;
import com.example.darcy_api.repository.StudentRepository;
import com.example.darcy_api.repository.VirtualClassroomRepository;
import com.example.darcy_api.service.StudentDataService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public StudentData createStudentData(StudentDataRequestDTO studentDataRequestDTO){
        Student student = studentRepository
                .findById(studentDataRequestDTO.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhum estudante com o id informado."));
        VirtualClassroom virtualClassroom = virtualClassroomRepository
                .findById(studentDataRequestDTO.getVirtualClassroomId())
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhum ambiente virtual com o id informado."));

        StudentData studentData = new StudentData();
        studentData.setStudent(student);
        studentData.setVirtualClassroom(virtualClassroom);
        studentData.setEstadoEmocional(studentDataRequestDTO.getEstadoEmocional());
        studentData.setGrauAutoconfianca(studentDataRequestDTO.getGrauAutoconfianca());
        studentData.setGrauInteresse(studentDataRequestDTO.getGrauInteresse());
        studentData.setGrauCompreensao(studentDataRequestDTO.getGrauCompreensao());
        studentData.setNecessidadeReforco(studentDataRequestDTO.getNecessidadeReforco());
        studentData.setSatisfacaoGeral(studentDataRequestDTO.getSatisfacaoGeral());
        studentData.setTopicoDificuldade(studentDataRequestDTO.getTopicoDificuldade());
        studentData.setTempoDedicadoEstudo(studentDataRequestDTO.getTempoDedicadoEstudo());

        return studentDataRepository.save(studentData);
    }

    @Override
    public StudentData updateStudentDataByStudentId(UUID studentId, StudentDataUpdateDTO studentDataUpdateDTO){
        StudentData studentData = getStudentDataByStudentId(studentId);

        studentData.setTopicoDificuldade(
                studentDataUpdateDTO.getTopicoDificuldade() != null ? studentDataUpdateDTO.getTopicoDificuldade() : studentData.getTopicoDificuldade());
        studentData.setTempoDedicadoEstudo(
                studentDataUpdateDTO.getTempoDedicadoEstudo() != null ? studentDataUpdateDTO.getTempoDedicadoEstudo() : studentData.getTempoDedicadoEstudo());
        studentData.setTempoDedicadoEstudo(
                studentDataUpdateDTO.getTempoDedicadoEstudo() != null ? studentDataUpdateDTO.getTempoDedicadoEstudo() : null);
        studentData.setSatisfacaoGeral(
                studentDataUpdateDTO.getSatisfacaoGeral() != null ? studentDataUpdateDTO.getSatisfacaoGeral() : studentData.getSatisfacaoGeral());
        studentData.setEstadoEmocional(
                studentDataUpdateDTO.getEstadoEmocional() != null ? studentDataUpdateDTO.getEstadoEmocional() : studentData.getEstadoEmocional());
        studentData.setGrauAutoconfianca(
                studentDataUpdateDTO.getGrauAutoconfianca() != null ? studentDataUpdateDTO.getGrauAutoconfianca() : studentData.getGrauAutoconfianca());
        studentData.setGrauCompreensao(
                studentDataUpdateDTO.getGrauCompreensao() != null ? studentDataUpdateDTO.getGrauCompreensao() : studentData.getGrauCompreensao());
        studentData.setNecessidadeReforco(
                studentDataUpdateDTO.getNecessidadeReforco() != null ? studentDataUpdateDTO.getNecessidadeReforco() : studentData.getNecessidadeReforco());

        return studentDataRepository.save(studentData);
    }

    @Override
    public void deleteStudentDataByStudentId(UUID studentId){
        getStudentDataByStudentId(studentId);
        studentDataRepository.deleteById(studentId);
    }
}
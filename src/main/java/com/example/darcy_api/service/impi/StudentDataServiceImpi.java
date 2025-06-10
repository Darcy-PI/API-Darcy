package com.example.darcy_api.service.impi;

import com.example.darcy_api.dto.request.StudentDataRequestDTO;
import com.example.darcy_api.dto.response.StudentDataResponseDTO;
import com.example.darcy_api.dto.update.StudentDataUpdateDTO;
import com.example.darcy_api.mapper.StudentDataMapper;
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

    private final StudentDataMapper studentDataMapper;

    public StudentDataServiceImpi(StudentDataRepository studentDataRepository, VirtualClassroomRepository virtualClassroomRepository, StudentRepository studentRepository, StudentDataMapper studentDataMapper) {
        this.studentDataRepository = studentDataRepository;
        this.virtualClassroomRepository = virtualClassroomRepository;
        this.studentRepository = studentRepository;

        this.studentDataMapper = studentDataMapper;
    }

    @Override
    public List<StudentDataResponseDTO> getAllStudentData(){
        return studentDataMapper.toDTOList(studentDataRepository.findAll());
    }

    @Override
    public List<StudentDataResponseDTO> getAllStudentDataByVirtualClassroomId(UUID virtualClassroomId){
        virtualClassroomRepository
                .findById(virtualClassroomId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum ambiente virtual com esse id encontrado.")
                );

        List<StudentData> studentDataList = studentDataRepository.findAllByVirtualClassroomId(virtualClassroomId);
        return studentDataMapper.toDTOList(studentDataList);
    }

    @Override
    public StudentDataResponseDTO getStudentDataByStudentId(UUID studentId){
        StudentData studentData = studentDataRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estudante com respostas registradas com esse id."));
        return studentDataMapper.toDTO(studentData);
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

        if (!virtualClassroom.getEstudantes().contains(student))
            throw new IllegalArgumentException("O estudante fornecido pelo id não pertence ao ambiente virtual indicado.");

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
        StudentData studentData = studentDataRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estudante com respostas registradas com esse id."));

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
        StudentData studentData = studentDataRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum estudante com respostas registradas com esse id."));
        studentDataRepository.delete(studentData);
    }
}
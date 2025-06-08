package com.example.darcy_api.service.impi;

import com.example.darcy_api.dto.request.VirtualClassroomRequestDTO;
import com.example.darcy_api.dto.response.VirtualClassroomResponseDTO;
import com.example.darcy_api.dto.update.VirtualClassroomUpdateDTO;

import com.example.darcy_api.mapper.VirtualClassroomMapper;
import com.example.darcy_api.model.Professor;
import com.example.darcy_api.model.Student;
import com.example.darcy_api.model.VirtualClassroom;

import com.example.darcy_api.repository.ProfessorRepository;
import com.example.darcy_api.repository.StudentRepository;
import com.example.darcy_api.repository.VirtualClassroomRepository;

import com.example.darcy_api.service.VirtualClassroomService;
import com.example.darcy_api.utils.Accesskey;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VirtualClassroomServiceImpi implements VirtualClassroomService {

    private final VirtualClassroomRepository virtualClassroomRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    private final VirtualClassroomMapper virtualClassroomMapper;

    public VirtualClassroomServiceImpi(VirtualClassroomRepository virtualClassroomRepository, StudentRepository studentRepository, ProfessorRepository professorRepository, VirtualClassroomMapper virtualClassroomMapper) {
        this.virtualClassroomRepository = virtualClassroomRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;

        this.virtualClassroomMapper = virtualClassroomMapper;
    }

    @Override
    public List<VirtualClassroom> getAllVirtualClassrooms() {
        return virtualClassroomRepository.findAll();
    }

    @Override
    public List<VirtualClassroomResponseDTO> getAllVirtualClassroomsByProfessorId(UUID professorId) {
        professorRepository
                .findById(professorId)
                .orElseThrow(() -> new EntityNotFoundException("Professor com o id informado não encontrado ou inexistente"));
        List<VirtualClassroom> ambientes = virtualClassroomRepository.findAllByProfessorDonoId(professorId);
        return virtualClassroomMapper.toDTOList(ambientes);
    }

    @Override
    @Transactional
    public List<VirtualClassroomResponseDTO> getAllVirtualClassroomsByStudentId(UUID studentId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Estudante com o id informado não encontrado ou inexistente"));
        List<VirtualClassroom> ambientes = student.getAmbientes();
        return virtualClassroomMapper.toDTOList(ambientes);
    }

    @Override
    public VirtualClassroom getVirtualClassroomById(UUID id){
        return virtualClassroomRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VirtualClassroom com o id informado não encontrado.")
                );
    }

    @Override
    public List<Student> getAllStudentsByVirtualClassroomId(UUID id){
        VirtualClassroom virtualClassroom = getVirtualClassroomById(id);
        return virtualClassroom.getEstudantes();
    }

    @Override
    public void regenerateAccessKeyByVirtualClassroomId(UUID id){
        VirtualClassroom virtualClassroom = getVirtualClassroomById(id);
        String accessKey = Accesskey.generateKey(6);

        virtualClassroom.setChaveAcesso(accessKey);
        virtualClassroomRepository.save(virtualClassroom);
    }

    @Override
    @Transactional
    public List<Student> addStudentToVirtualClassroom(UUID studentId, String accessKey){
        VirtualClassroom virtualClassroom = virtualClassroomRepository
                .getByChaveAcesso(accessKey)
                .orElseThrow(() -> new EntityNotFoundException("Ambiente virtual não encontrado ou inexistente com esse id"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Nenhum estudante encontrado com esse id"));

        if (!accessKey.equals(virtualClassroom.getChaveAcesso()))
            throw new IllegalArgumentException("A chave de acesso inserida está incorreta ou inexistente");

        if (virtualClassroom.getEstudantes().contains(student))
            throw new IllegalStateException("Esse estudante já está presente nesse ambiente virtual");

        virtualClassroom.getEstudantes().add(student);
        student.getAmbientes().add(virtualClassroom);

        virtualClassroomRepository.save(virtualClassroom);
        studentRepository.save(student);

        return getAllStudentsByVirtualClassroomId(virtualClassroom.getId());
    }

    @Override
    @Transactional
    public VirtualClassroom createVirtualClassroom(VirtualClassroomRequestDTO virtualClassroomRequestDTO){
        Professor professor = professorRepository.findById(virtualClassroomRequestDTO
                        .getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Nenhum professor com o id informado encontrado"));
        String accessKey = Accesskey.generateKey(6);

        VirtualClassroom virtualClassroom = new VirtualClassroom();
        virtualClassroom.setProfessorDono(professor);
        virtualClassroom.setChaveAcesso(accessKey);
        virtualClassroom.setNomeAmbiente(virtualClassroomRequestDTO.getNomeAmbiente());
        virtualClassroom.setMateria(virtualClassroomRequestDTO.getMateria());
        virtualClassroom.setSerie(virtualClassroomRequestDTO.getSerie());
        virtualClassroom.setEstudantes(new ArrayList<>());

        professor.getAmbientes().add(virtualClassroom);
        virtualClassroomRepository.save(virtualClassroom);
        professorRepository.save(professor);

        return virtualClassroom;
    }

    @Override
    public VirtualClassroom updateVirtualClassroomById(UUID id, VirtualClassroomUpdateDTO virtualClassroomUpdateDTO){
        VirtualClassroom virtualClassroom = getVirtualClassroomById(id);

        virtualClassroom.setMateria(
                virtualClassroomUpdateDTO.getMateria() != null ? virtualClassroomUpdateDTO.getMateria() : virtualClassroom.getMateria());
        virtualClassroom.setSerie(
                virtualClassroomUpdateDTO.getSerie() != null ? virtualClassroomUpdateDTO.getSerie() : virtualClassroom.getSerie());
        virtualClassroom.setNomeAmbiente(
                virtualClassroomUpdateDTO.getNomeAmbiente() != null ? virtualClassroomUpdateDTO.getNomeAmbiente() : virtualClassroom.getNomeAmbiente());

        return virtualClassroomRepository.save(virtualClassroom);
    }

    @Override
    public void deleteVirtualClassroomById(UUID id){
        getVirtualClassroomById(id);
        virtualClassroomRepository.deleteById(id);
    }
}
package com.example.darcy_api.service.impi;

import com.example.darcy_api.service.StudentService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.darcy_api.repository.StudentRepository;
import com.example.darcy_api.repository.VirtualClassroomRepository;
import com.example.darcy_api.dto.update.StudentUpdateDTO;
import com.example.darcy_api.model.Student;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpi implements StudentService {

    private final StudentRepository studentRepository;
    private final VirtualClassroomRepository virtualClassroomRepository;

    public StudentServiceImpi(StudentRepository studentRepository, VirtualClassroomRepository virtualClassroomRepository) {
        this.studentRepository = studentRepository;
        this.virtualClassroomRepository = virtualClassroomRepository;
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(UUID id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Estudante com o id informado não encontrado ou inexistente."));
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudentById(UUID id, StudentUpdateDTO studentUpdateDTO) {
        Student student = getStudentById(id);

        student.setUsuario(
            studentUpdateDTO.getUsuario() != null ? studentUpdateDTO.getUsuario() : student.getUsuario());
        student.setSenha(
            studentUpdateDTO.getSenha() != null ? studentUpdateDTO.getSenha() : student.getSenha());
        student.setNomeCompleto(
            studentUpdateDTO.getNomeCompleto() != null ? studentUpdateDTO.getNomeCompleto() : student.getNomeCompleto());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(UUID id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}

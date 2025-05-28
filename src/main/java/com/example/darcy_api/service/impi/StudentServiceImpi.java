package com.example.darcy_api.service.impi;

import com.example.darcy_api.model.VirtualClassroom;
import com.example.darcy_api.service.StudentService;
import org.springframework.stereotype.Service;
import com.example.darcy_api.repository.StudentRepository;
import com.example.darcy_api.model.Student;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpi implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpi(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public List<VirtualClassroom> getAllStudentVirtualClassroomsByStudentId(UUID id){
        return null;
    }

    @Override
    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student createStudent(Student aluno) {
        return studentRepository.save(aluno);
    }

    @Override
    public Student updateStudentById(UUID id, Student newAluno) {
        Student existingAluno = studentRepository.findById(id).orElse(null);
        if (existingAluno != null) {
            return studentRepository.save(existingAluno);
        }
        return null;
    }

    @Override
    public void deleteStudentById(UUID id) {
        studentRepository.deleteById(id);
    }
}

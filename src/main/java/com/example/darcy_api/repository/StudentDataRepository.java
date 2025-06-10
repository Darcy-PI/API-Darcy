package com.example.darcy_api.repository;

import com.example.darcy_api.model.StudentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDataRepository extends JpaRepository<StudentData, UUID> {
    List<StudentData> findAllByVirtualClassroomId(UUID virtualClassroomId);

    Optional<StudentData> findByStudentId(UUID studentId);
}

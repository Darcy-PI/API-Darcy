package com.example.darcy_api.mapper;

import com.example.darcy_api.dto.response.StudentDataResponseDTO;
import com.example.darcy_api.model.StudentData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentDataMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "virtualClassroom.id", target = "virtualClassroomId")
    List<StudentDataResponseDTO> toDTOList(List<StudentData> studentDataList);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "virtualClassroom.id", target = "virtualClassroomId")
    StudentDataResponseDTO toDTO(StudentData studentData);
}

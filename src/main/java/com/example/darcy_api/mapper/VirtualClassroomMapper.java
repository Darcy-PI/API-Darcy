package com.example.darcy_api.mapper;

import com.example.darcy_api.dto.response.VirtualClassroomResponseDTO;
import com.example.darcy_api.model.VirtualClassroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VirtualClassroomMapper {

    List<VirtualClassroomResponseDTO> toDTOList(List<VirtualClassroom> virtualClassrooms);
}

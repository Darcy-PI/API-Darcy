package com.example.darcy_api.service;

import java.util.List;
import java.util.UUID;

import com.example.darcy_api.dto.SchoolUpdateDTO;
import com.example.darcy_api.model.School;

public interface SchoolService {
    List<School> getAllSchools();

    School getSchoolById(UUID id);

    School createSchool(School school);

    School updateSchoolById(UUID id, SchoolUpdateDTO schoolUpdateDTO);

    void deleteSchoolById(UUID id);
    
    
}
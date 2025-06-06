package com.example.darcy_api.service.impi;

import com.example.darcy_api.dto.SchoolUpdateDTO;
import com.example.darcy_api.model.School;
import com.example.darcy_api.repository.SchoolRepository;
import com.example.darcy_api.service.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolServiceImpi implements SchoolService{
    private SchoolRepository schoolRepository;

    public SchoolServiceImpi(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAllSchools(){
        return schoolRepository.findAll();
    }

    public School getSchoolById(UUID id){
        return schoolRepository
            .findById(id)
            .orElseThrow(()-> new EntityNotFound("Escola não encontrada com esse id !"));
    }

    public School createSchool(School school){
        return schoolRepository.save(school);
    }

    public School updateSchoolById(UUID id, SchoolUpdateDTO schoolUpdateDTO){
         School school = getSchoolById(id);

        School.setnomeEscola(
            schoolUpdateDTO.getnomeEscola() != null ? schoolUpdateDTO.getnomeEscola() : school.getnomeEscola());
        school.setendereco(
            schoolUpdateDTO.getendereco() != null ? schoolUpdateDTO.getendereco() : school.getendereco());
        school.setnumeroCordenacao(
            schoolUpdateDTO.getnumeroCordenacao() != null ? schoolUpdateDTO.getnumeroCordenacao() : school.getnumeroCordenacao());

        return schoolRepository.save(school);
    }

    
    public void deleteSchoolById(UUID id){
        getSchoolById(id);
        schoolRepository.deleteById(id);
    }

}
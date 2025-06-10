package com.example.darcy_api.service.impi;

import com.example.darcy_api.dto.request.SchoolRequestDTO;
import com.example.darcy_api.dto.update.SchoolUpdateDTO;
import com.example.darcy_api.model.Pedagogue;
import com.example.darcy_api.model.School;
import com.example.darcy_api.repository.PedagogueRepository;
import com.example.darcy_api.repository.SchoolRepository;
import com.example.darcy_api.service.SchoolService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolServiceImpi implements SchoolService{
    private SchoolRepository schoolRepository;

    private PedagogueRepository pedagogueRepository;

    public SchoolServiceImpi(SchoolRepository schoolRepository, PedagogueRepository pedagogueRepository) {
        this.schoolRepository = schoolRepository;
        this.pedagogueRepository = pedagogueRepository;
    }

    public List<School> getAllSchools(){
        return schoolRepository.findAll();
    }

    public School getSchoolById(UUID id){
        return schoolRepository
            .findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Escola não encontrada com esse id !"));
    }

    public School createSchool(SchoolRequestDTO schoolRequestDTO){
        Pedagogue pedagogue = pedagogueRepository
                .findById(schoolRequestDTO.getPedagogueId())
                .orElseThrow(()->new EntityNotFoundException("Nenhum pedagogue encontrado com esse id !"));

        School school = new School();
        school.setPedagogue(pedagogue);
        school.setTurnoEnsino(schoolRequestDTO.getTurnoEnsino());
        school.setTipoEscola(schoolRequestDTO.getTipoEscola());
        school.setNumeroCordenacao(schoolRequestDTO.getNumeroCordenacao());
        school.setEndereco(schoolRequestDTO.getEndereco());
        school.setNomeEscola(schoolRequestDTO.getNomeEscola());

        return schoolRepository.save(school);
    }

    public School updateSchoolById(UUID id, SchoolUpdateDTO schoolUpdateDTO){
         School school = getSchoolById(id);

        school.setNomeEscola(
            schoolUpdateDTO.getNomeEscola() != null ? schoolUpdateDTO.getNomeEscola() : school.getNomeEscola());
        school.setEndereco(
            schoolUpdateDTO.getEndereco() != null ? schoolUpdateDTO.getEndereco() : school.getEndereco());
        school.setNumeroCordenacao(
            schoolUpdateDTO.getNumeroCordenacao() != null ? schoolUpdateDTO.getNumeroCordenacao() : school.getNumeroCordenacao());

        return schoolRepository.save(school);
    }

    
    public void deleteSchoolById(UUID id){
        getSchoolById(id);
        schoolRepository.deleteById(id);
    }

}
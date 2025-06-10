package com.example.darcy_api.repository;

import com.example.darcy_api.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository<School, UUID> {
    

}